package com.osrapi.controllers.lablord;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osrapi.models.lablord.LABLORDAbilityEntity;
import com.osrapi.models.lablord.LABLORDAbilityRequirementEntity;
import com.osrapi.models.lablord.LABLORDCharacterClassEntity;
import com.osrapi.models.lablord.LABLORDClassAbilityEntity;
import com.osrapi.models.lablord.LABLORDDieRollEntity;
import com.osrapi.models.lablord.LABLORDLanguageEntity;
import com.osrapi.repositories.lablord.LABLORDCharacterClassRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/character_classes")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDCharacterClassController {
	/** the static instance of {@link LABLORDCharacterClassController}. */
	private static LABLORDCharacterClassController instance;
	/**
	 * Gets the static instance.
	 * @return {@link LABLORDCharacterClassController}
	 */
	public static LABLORDCharacterClassController getInstance() {
		if (instance == null) {
			new LABLORDCharacterClassController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private LABLORDCharacterClassRepository repository;
	/** Creates a new instance of {@link LABLORDCharacterClassController}. */
	public LABLORDCharacterClassController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link LABLORDCharacterClassEntity}s.
	 * @return {@link List}<{@link Resource}<{@link LABLORDCharacterClassEntity}
	 *         >>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<LABLORDCharacterClassEntity>> getAll() {
		Iterator<LABLORDCharacterClassEntity> iter = repository.findAll()
				.iterator();
		List<Resource<LABLORDCharacterClassEntity>> resources = new ArrayList<Resource<LABLORDCharacterClassEntity>>();
		while (iter.hasNext()) {
			resources.add(getCharacterClassResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDCharacterClassEntity}s that share a
	 * description.
	 * @param description the character_class's description
	 * @return {@link List}<{@link Resource}<{@link LABLORDCharacterClassEntity}
	 *         >>
	 */
	@RequestMapping(path = "description/{description}", method = RequestMethod.GET)
	public List<Resource<LABLORDCharacterClassEntity>> getByDescription(
			@PathVariable final String description) {
		Iterator<LABLORDCharacterClassEntity> iter = repository
				.findByDescription(description)
				.iterator();
		List<Resource<LABLORDCharacterClassEntity>> resources = new ArrayList<Resource<LABLORDCharacterClassEntity>>();
		while (iter.hasNext()) {
			resources.add(getCharacterClassResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link LABLORDCharacterClassEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link LABLORDCharacterClassEntity}
	 *         >>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<LABLORDCharacterClassEntity>> getById(
			@PathVariable final Long id) {
		LABLORDCharacterClassEntity entity = repository.findOne(id);
		List<Resource<LABLORDCharacterClassEntity>> resources = new ArrayList<Resource<LABLORDCharacterClassEntity>>();
		resources.add(getCharacterClassResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDCharacterClassEntity}s that share a
	 * maxLevel.
	 * @param maxLevel the character_class's maxLevel
	 * @return {@link List}<{@link Resource}<{@link LABLORDCharacterClassEntity}
	 *         >>
	 */
	@RequestMapping(path = "max_level/{maxLevel}", method = RequestMethod.GET)
	public List<Resource<LABLORDCharacterClassEntity>> getByMaxLevel(
			@PathVariable final Long maxLevel) {
		Iterator<LABLORDCharacterClassEntity> iter = repository
				.findByMaxLevel(maxLevel)
				.iterator();
		List<Resource<LABLORDCharacterClassEntity>> resources = new ArrayList<Resource<LABLORDCharacterClassEntity>>();
		while (iter.hasNext()) {
			resources.add(getCharacterClassResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDCharacterClassEntity}s that share a name.
	 * @param name the character_class's name
	 * @return {@link List}<{@link Resource}<{@link LABLORDCharacterClassEntity}
	 *         >>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<LABLORDCharacterClassEntity>> getByName(
			@PathVariable final String name) {
		Iterator<LABLORDCharacterClassEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<LABLORDCharacterClassEntity>> resources = new ArrayList<Resource<LABLORDCharacterClassEntity>>();
		while (iter.hasNext()) {
			resources.add(getCharacterClassResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link LABLORDCharacterClassEntity}.
	 * @param entity the {@link LABLORDCharacterClassEntity}
	 * @return {@link Resource}<{@link LABLORDCharacterClassEntity}>
	 */
	private Resource<LABLORDCharacterClassEntity> getCharacterClassResource(
			final LABLORDCharacterClassEntity entity) {
		Resource<LABLORDCharacterClassEntity> resource = new Resource<LABLORDCharacterClassEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}
	/**
	 * Saves a single {@link LABLORDCharacterClassEntity}.
	 * @param entity the {@link LABLORDCharacterClassEntity} instance
	 * @return {@link List}<{@link Resource}<{@link LABLORDCharacterClassEntity}
	 *         >>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<LABLORDCharacterClassEntity>> save(
			@RequestBody final LABLORDCharacterClassEntity entity) {
		if (entity.getAbilities() != null
				&& !entity.getAbilities().isEmpty()) {
			for (int i = entity.getAbilities().size() - 1; i >= 0; i--) {
				LABLORDClassAbilityEntity abilities = null;
				List<Resource<LABLORDClassAbilityEntity>> list = null;
				try {
					Method method = null;
					try {
						method = LABLORDClassAbilityController.class
								.getDeclaredMethod(
										"getByName",
										new Class[] { String.class });
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					Field field = null;
					try {
						field = LABLORDClassAbilityEntity.class
								.getDeclaredField("name");
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getAbilities().get(i)) != null) {
							list = (List<Resource<LABLORDClassAbilityEntity>>) method
									.invoke(
											LABLORDClassAbilityController
													.getInstance(),
											(String) field.get(entity
													.getAbilities().get(i)));
						}
					}
					if (list == null) {
						try {
							method = LABLORDClassAbilityController.class
									.getDeclaredMethod(
											"getByCode",
											new Class[] { String.class });
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
						try {
							field = LABLORDClassAbilityEntity.class
									.getDeclaredField(
											"code");
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getAbilities().get(i)) != null) {
								list = (List<Resource<LABLORDClassAbilityEntity>>) method
										.invoke(
												LABLORDClassAbilityController
														.getInstance(),
												(String) field
														.get(entity
																.getAbilities()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (SecurityException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					abilities = list.get(0).getContent();
				}
				if (abilities == null) {
					abilities = (LABLORDClassAbilityEntity) ((Resource) LABLORDClassAbilityController
							.getInstance()
							.save(entity.getAbilities().get(i)).get(0))
									.getContent();
				}
				entity.getAbilities().set(i, abilities);
				list = null;
			}
		}

		if (entity.getHitDice() != null
				&& entity.getHitDice().getId() == null) {
			setHitDiceIdFromRepository(entity);
		}

		if (entity.getLanguages() != null
				&& !entity.getLanguages().isEmpty()) {
			for (int i = entity.getLanguages().size() - 1; i >= 0; i--) {
				LABLORDLanguageEntity languages = null;
				List<Resource<LABLORDLanguageEntity>> list = null;
				try {
					Method method = null;
					try {
						method = LABLORDLanguageController.class
								.getDeclaredMethod(
										"getByName",
										new Class[] { String.class });
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					Field field = null;
					try {
						field = LABLORDLanguageEntity.class
								.getDeclaredField("name");
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getLanguages().get(i)) != null) {
							list = (List<Resource<LABLORDLanguageEntity>>) method
									.invoke(
											LABLORDLanguageController
													.getInstance(),
											(String) field.get(entity
													.getLanguages().get(i)));
						}
					}
					if (list == null) {
						try {
							method = LABLORDLanguageController.class
									.getDeclaredMethod(
											"getByCode",
											new Class[] { String.class });
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
						try {
							field = LABLORDLanguageEntity.class
									.getDeclaredField(
											"code");
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getLanguages().get(i)) != null) {
								list = (List<Resource<LABLORDLanguageEntity>>) method
										.invoke(
												LABLORDLanguageController
														.getInstance(),
												(String) field
														.get(entity
																.getLanguages()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (SecurityException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					languages = list.get(0).getContent();
				}
				if (languages == null) {
					languages = (LABLORDLanguageEntity) ((Resource) LABLORDLanguageController
							.getInstance()
							.save(entity.getLanguages().get(i)).get(0))
									.getContent();
				}
				entity.getLanguages().set(i, languages);
				list = null;
			}
		}

		if (entity.getPrimeRequisites() != null
				&& !entity.getPrimeRequisites().isEmpty()) {
			for (int i = entity.getPrimeRequisites().size() - 1; i >= 0; i--) {
				LABLORDAbilityEntity primeRequisites = null;
				List<Resource<LABLORDAbilityEntity>> list = null;
				try {
					Method method = null;
					try {
						method = LABLORDAbilityController.class
								.getDeclaredMethod(
										"getByName",
										new Class[] { String.class });
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					Field field = null;
					try {
						field = LABLORDAbilityEntity.class
								.getDeclaredField("name");
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(
								entity.getPrimeRequisites().get(i)) != null) {
							list = (List<Resource<LABLORDAbilityEntity>>) method
									.invoke(
											LABLORDAbilityController
													.getInstance(),
											(String) field.get(
													entity.getPrimeRequisites()
															.get(i)));
						}
					}
					if (list == null) {
						try {
							method = LABLORDAbilityController.class
									.getDeclaredMethod(
											"getByCode",
											new Class[] { String.class });
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
						try {
							field = LABLORDAbilityEntity.class.getDeclaredField(
									"code");
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(entity.getPrimeRequisites()
									.get(i)) != null) {
								list = (List<Resource<LABLORDAbilityEntity>>) method
										.invoke(
												LABLORDAbilityController
														.getInstance(),
												(String) field
														.get(entity
																.getPrimeRequisites()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (SecurityException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					primeRequisites = list.get(0).getContent();
				}
				if (primeRequisites == null) {
					primeRequisites = (LABLORDAbilityEntity) ((Resource) LABLORDAbilityController
							.getInstance()
							.save(entity.getPrimeRequisites().get(i)).get(0))
									.getContent();
				}
				entity.getPrimeRequisites().set(i, primeRequisites);
				list = null;
			}
		}

		if (entity.getRequirements() != null
				&& !entity.getRequirements().isEmpty()) {
			for (int i = entity.getRequirements().size() - 1; i >= 0; i--) {
				LABLORDAbilityRequirementEntity requirements = null;
				List<Resource<LABLORDAbilityRequirementEntity>> list = null;
				try {
					Method method = null;
					try {
						method = LABLORDAbilityRequirementController.class
								.getDeclaredMethod(
										"getByName",
										new Class[] { String.class });
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					Field field = null;
					try {
						field = LABLORDAbilityRequirementEntity.class
								.getDeclaredField("name");
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field
								.get(entity.getRequirements().get(i)) != null) {
							list = (List<Resource<LABLORDAbilityRequirementEntity>>) method
									.invoke(
											LABLORDAbilityRequirementController
													.getInstance(),
											(String) field.get(entity
													.getRequirements().get(i)));
						}
					}
					if (list == null) {
						try {
							method = LABLORDAbilityRequirementController.class
									.getDeclaredMethod(
											"getByCode",
											new Class[] { String.class });
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
						try {
							field = LABLORDAbilityRequirementEntity.class
									.getDeclaredField(
											"code");
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getRequirements().get(i)) != null) {
								list = (List<Resource<LABLORDAbilityRequirementEntity>>) method
										.invoke(
												LABLORDAbilityRequirementController
														.getInstance(),
												(String) field
														.get(entity
																.getRequirements()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (SecurityException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					requirements = list.get(0).getContent();
				}
				if (requirements == null) {
					requirements = (LABLORDAbilityRequirementEntity) ((Resource) LABLORDAbilityRequirementController
							.getInstance()
							.save(entity.getRequirements().get(i)).get(0))
									.getContent();
				}
				entity.getRequirements().set(i, requirements);
				list = null;
			}
		}

		LABLORDCharacterClassEntity savedEntity = repository.save(entity);
		List<Resource<LABLORDCharacterClassEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
	/**
	 * Saves multiple {@link LABLORDCharacterClassEntity}s.
	 * @param entities the list of {@link LABLORDCharacterClassEntity} instances
	 * @return {@link List}<{@link Resource}<{@link LABLORDCharacterClassEntity}
	 *         >>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<LABLORDCharacterClassEntity>> save(
			@RequestBody final List<LABLORDCharacterClassEntity> entities) {
		List<Resource<LABLORDCharacterClassEntity>> resources = new ArrayList<Resource<LABLORDCharacterClassEntity>>();
		Iterator<LABLORDCharacterClassEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}

	private void setHitDiceIdFromRepository(
			final LABLORDCharacterClassEntity entity) {
		LABLORDDieRollEntity memberEntity = null;
		List<Resource<LABLORDDieRollEntity>> list = null;
		try {
			Method method = null;
			Field field = null;
			try {
				method = LABLORDDieRollController.class.getDeclaredMethod(
						"getByName", new Class[] { String.class });
				field = LABLORDDieRollEntity.class.getDeclaredField("name");
			} catch (NoSuchMethodException | NoSuchFieldException e) {
			}
			if (method != null
					&& field != null) {
				field.setAccessible(true);
				if (field.get(entity.getHitDice()) != null) {
					list = (List<Resource<LABLORDDieRollEntity>>) method
							.invoke(
									LABLORDDieRollController.getInstance(),
									(String) field
											.get(entity.getHitDice()));
				}
			}
			if (list == null) {
				try {
					method = LABLORDDieRollController.class.getDeclaredMethod(
							"getByCode", new Class[] { String.class });
					field = LABLORDDieRollEntity.class
							.getDeclaredField("code");
				} catch (NoSuchMethodException | NoSuchFieldException e) {
				}
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity.getHitDice()) != null) {
						list = (List<Resource<LABLORDDieRollEntity>>) method
								.invoke(LABLORDDieRollController
										.getInstance(), (String) field.get(
												entity.getHitDice()));
					}
				}
			}
			method = null;
			field = null;
		} catch (SecurityException | IllegalArgumentException
				| IllegalAccessException
				| InvocationTargetException e) {
		}
		if (list != null
				&& !list.isEmpty()) {
			memberEntity = list.get(0).getContent();
		}
		if (memberEntity == null) {
			memberEntity = (LABLORDDieRollEntity) ((Resource) LABLORDDieRollController
					.getInstance().save(
							entity.getHitDice())
					.get(0)).getContent();
		}
		entity.setHitDice(memberEntity);
		list = null;
	}

	/**
	 * Tries to set the Id for an entity to be saved by locating it in the
	 * repository.
	 * @param entity the {@link LABLORDCharacterClassEntity} instance
	 */
	private void setIdFromRepository(final LABLORDCharacterClassEntity entity) {
		List<LABLORDCharacterClassEntity> old = null;
		try {
			Method method = null;
			Field field = null;
			try {
				method = repository.getClass().getDeclaredMethod(
						"findByName", new Class[] { String.class });
				field = LABLORDCharacterClassEntity.class
						.getDeclaredField("name");
			} catch (NoSuchMethodException | NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (method != null
					&& field != null) {
				field.setAccessible(true);
				if (field.get(entity) != null) {
					old = (List<LABLORDCharacterClassEntity>) method.invoke(
							repository, (String) field.get(entity));
				}
			}
			if (old == null
					|| old != null
							&& old.size() > 1) {
				try {
					method = repository.getClass().getDeclaredMethod(
							"findByCode", new Class[] { String.class });
					field = LABLORDCharacterClassEntity.class.getDeclaredField(
							"code");
				} catch (NoSuchMethodException | NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity) != null) {
						old = (List<LABLORDCharacterClassEntity>) method.invoke(
								repository, (String) field.get(entity));
					}
				}
			}
			method = null;
			field = null;
		} catch (SecurityException | IllegalArgumentException
				| IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		if (old != null
				&& old.size() == 1) {
			entity.setId(old.get(0).getId());
		}
		old = null;
	}
	/**
	 * Updates a single {@link LABLORDCharacterClassEntity}.
	 * @param entity the {@link LABLORDCharacterClassEntity} instance
	 * @return {@link List}<{@link Resource}<{@link LABLORDCharacterClassEntity}
	 *         >>
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public List<Resource<LABLORDCharacterClassEntity>> update(
			@RequestBody final LABLORDCharacterClassEntity entity) {
		if (entity.getId() == null) {
			setIdFromRepository(entity);
		}
		if (entity.getAbilities() != null
				&& !entity.getAbilities().isEmpty()) {
			for (int i = entity.getAbilities().size() - 1; i >= 0; i--) {
				LABLORDClassAbilityEntity abilities = null;
				List<Resource<LABLORDClassAbilityEntity>> list = null;
				try {
					Method method = null;
					try {
						method = LABLORDClassAbilityController.class
								.getDeclaredMethod(
										"getByName",
										new Class[] { String.class });
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					Field field = null;
					try {
						field = LABLORDClassAbilityEntity.class
								.getDeclaredField("name");
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getAbilities().get(i)) != null) {
							list = (List<Resource<LABLORDClassAbilityEntity>>) method
									.invoke(
											LABLORDClassAbilityController
													.getInstance(),
											(String) field.get(entity
													.getAbilities().get(i)));
						}
					}
					if (list == null) {
						try {
							method = LABLORDClassAbilityController.class
									.getDeclaredMethod(
											"getByCode",
											new Class[] { String.class });
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
						try {
							field = LABLORDClassAbilityEntity.class
									.getDeclaredField(
											"code");
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getAbilities().get(i)) != null) {
								list = (List<Resource<LABLORDClassAbilityEntity>>) method
										.invoke(
												LABLORDClassAbilityController
														.getInstance(),
												(String) field
														.get(entity
																.getAbilities()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (SecurityException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					abilities = list.get(0).getContent();
				}
				if (abilities == null) {
					abilities = (LABLORDClassAbilityEntity) ((Resource) LABLORDClassAbilityController
							.getInstance()
							.save(entity.getAbilities().get(i)).get(0))
									.getContent();
				}
				entity.getAbilities().set(i, abilities);
				list = null;
			}
		}

		if (entity.getHitDice() != null
				&& entity.getHitDice().getId() == null) {
			setHitDiceIdFromRepository(entity);
		}

		if (entity.getLanguages() != null
				&& !entity.getLanguages().isEmpty()) {
			for (int i = entity.getLanguages().size() - 1; i >= 0; i--) {
				LABLORDLanguageEntity languages = null;
				List<Resource<LABLORDLanguageEntity>> list = null;
				try {
					Method method = null;
					try {
						method = LABLORDLanguageController.class
								.getDeclaredMethod(
										"getByName",
										new Class[] { String.class });
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					Field field = null;
					try {
						field = LABLORDLanguageEntity.class
								.getDeclaredField("name");
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getLanguages().get(i)) != null) {
							list = (List<Resource<LABLORDLanguageEntity>>) method
									.invoke(
											LABLORDLanguageController
													.getInstance(),
											(String) field.get(entity
													.getLanguages().get(i)));
						}
					}
					if (list == null) {
						try {
							method = LABLORDLanguageController.class
									.getDeclaredMethod(
											"getByCode",
											new Class[] { String.class });
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
						try {
							field = LABLORDLanguageEntity.class
									.getDeclaredField(
											"code");
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getLanguages().get(i)) != null) {
								list = (List<Resource<LABLORDLanguageEntity>>) method
										.invoke(
												LABLORDLanguageController
														.getInstance(),
												(String) field
														.get(entity
																.getLanguages()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (SecurityException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					languages = list.get(0).getContent();
				}
				if (languages == null) {
					languages = (LABLORDLanguageEntity) ((Resource) LABLORDLanguageController
							.getInstance()
							.save(entity.getLanguages().get(i)).get(0))
									.getContent();
				}
				entity.getLanguages().set(i, languages);
				list = null;
			}
		}

		if (entity.getPrimeRequisites() != null
				&& !entity.getPrimeRequisites().isEmpty()) {
			for (int i = entity.getPrimeRequisites().size() - 1; i >= 0; i--) {
				LABLORDAbilityEntity primeRequisites = null;
				List<Resource<LABLORDAbilityEntity>> list = null;
				try {
					Method method = null;
					try {
						method = LABLORDAbilityController.class
								.getDeclaredMethod(
										"getByName",
										new Class[] { String.class });
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					Field field = null;
					try {
						field = LABLORDAbilityEntity.class
								.getDeclaredField("name");
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(
								entity.getPrimeRequisites().get(i)) != null) {
							list = (List<Resource<LABLORDAbilityEntity>>) method
									.invoke(
											LABLORDAbilityController
													.getInstance(),
											(String) field.get(
													entity.getPrimeRequisites()
															.get(i)));
						}
					}
					if (list == null) {
						try {
							method = LABLORDAbilityController.class
									.getDeclaredMethod(
											"getByCode",
											new Class[] { String.class });
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
						try {
							field = LABLORDAbilityEntity.class.getDeclaredField(
									"code");
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(entity.getPrimeRequisites()
									.get(i)) != null) {
								list = (List<Resource<LABLORDAbilityEntity>>) method
										.invoke(
												LABLORDAbilityController
														.getInstance(),
												(String) field
														.get(entity
																.getPrimeRequisites()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (SecurityException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					primeRequisites = list.get(0).getContent();
				}
				if (primeRequisites == null) {
					primeRequisites = (LABLORDAbilityEntity) ((Resource) LABLORDAbilityController
							.getInstance()
							.save(entity.getPrimeRequisites().get(i)).get(0))
									.getContent();
				}
				entity.getPrimeRequisites().set(i, primeRequisites);
				list = null;
			}
		}

		if (entity.getRequirements() != null
				&& !entity.getRequirements().isEmpty()) {
			for (int i = entity.getRequirements().size() - 1; i >= 0; i--) {
				LABLORDAbilityRequirementEntity requirements = null;
				List<Resource<LABLORDAbilityRequirementEntity>> list = null;
				try {
					Method method = null;
					try {
						method = LABLORDAbilityRequirementController.class
								.getDeclaredMethod(
										"getByName",
										new Class[] { String.class });
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					Field field = null;
					try {
						field = LABLORDAbilityRequirementEntity.class
								.getDeclaredField("name");
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field
								.get(entity.getRequirements().get(i)) != null) {
							list = (List<Resource<LABLORDAbilityRequirementEntity>>) method
									.invoke(
											LABLORDAbilityRequirementController
													.getInstance(),
											(String) field.get(entity
													.getRequirements().get(i)));
						}
					}
					if (list == null) {
						try {
							method = LABLORDAbilityRequirementController.class
									.getDeclaredMethod(
											"getByCode",
											new Class[] { String.class });
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
						try {
							field = LABLORDAbilityRequirementEntity.class
									.getDeclaredField(
											"code");
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getRequirements().get(i)) != null) {
								list = (List<Resource<LABLORDAbilityRequirementEntity>>) method
										.invoke(
												LABLORDAbilityRequirementController
														.getInstance(),
												(String) field
														.get(entity
																.getRequirements()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (SecurityException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					requirements = list.get(0).getContent();
				}
				if (requirements == null) {
					requirements = (LABLORDAbilityRequirementEntity) ((Resource) LABLORDAbilityRequirementController
							.getInstance()
							.save(entity.getRequirements().get(i)).get(0))
									.getContent();
				}
				entity.getRequirements().set(i, requirements);
				list = null;
			}
		}

		LABLORDCharacterClassEntity savedEntity = repository.save(entity);
		List<Resource<LABLORDCharacterClassEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
	/**
	 * Updates multiple {@link LABLORDCharacterClassEntity}s.
	 * @param entities the list of {@link LABLORDCharacterClassEntity} instances
	 * @return {@link List}<{@link Resource}<{@link LABLORDCharacterClassEntity}
	 *         >>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.PUT)
	public List<Resource<LABLORDCharacterClassEntity>> update(
			@RequestBody final List<LABLORDCharacterClassEntity> entities) {
		List<Resource<LABLORDCharacterClassEntity>> resources = new ArrayList<Resource<LABLORDCharacterClassEntity>>();
		Iterator<LABLORDCharacterClassEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(update(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
}
