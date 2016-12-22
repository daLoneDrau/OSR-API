package com.osrapi.controllers.wfrp;

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

import com.osrapi.models.wfrp.WFRPCareerEntity;
import com.osrapi.models.wfrp.WFRPCareerTypeEntity;
import com.osrapi.models.wfrp.WFRPEquipmentEntity;
import com.osrapi.models.wfrp.WFRPGenderEntity;
import com.osrapi.models.wfrp.WFRPRaceEntity;
import com.osrapi.models.wfrp.WFRPSkillChoiceEntity;
import com.osrapi.models.wfrp.WFRPSkillEntity;
import com.osrapi.models.wfrp.WFRPSourcebookEntity;
import com.osrapi.models.wfrp.WFRPTalentChoiceEntity;
import com.osrapi.models.wfrp.WFRPTalentEntity;
import com.osrapi.repositories.wfrp.WFRPCareerRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/careers")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPCareerController {
	/** the static instance of {@link WFRPCareerController}. */
	private static WFRPCareerController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPCareerController}
	 */
	public static WFRPCareerController getInstance() {
		if (instance == null) {
			new WFRPCareerController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPCareerRepository repository;
	/** Creates a new instance of {@link WFRPCareerController}. */
	public WFRPCareerController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getAll() {
		Iterator<WFRPCareerEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceA.
	 * @param advanceA the career' advanceA
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_a/{advanceA}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceA(
			@PathVariable final Long advanceA) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceA(advanceA)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceAg.
	 * @param advanceAg the career' advanceAg
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_ag/{advanceAg}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceAg(
			@PathVariable final Long advanceAg) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceAg(advanceAg)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceBs.
	 * @param advanceBs the career' advanceBs
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_bs/{advanceBs}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceBs(
			@PathVariable final Long advanceBs) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceBs(advanceBs)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceFel.
	 * @param advanceFel the career' advanceFel
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_fel/{advanceFel}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceFel(
			@PathVariable final Long advanceFel) {
		Iterator<WFRPCareerEntity> iter = repository
				.findByAdvanceFel(advanceFel)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}

	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceFp.
	 * @param advanceFp the career' advanceFp
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_fp/{advanceFp}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceFp(
			@PathVariable final Long advanceFp) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceFp(advanceFp)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceInt.
	 * @param advanceInt the career' advanceInt
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_int/{advanceInt}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceInt(
			@PathVariable final Long advanceInt) {
		Iterator<WFRPCareerEntity> iter = repository
				.findByAdvanceInt(advanceInt)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceIp.
	 * @param advanceIp the career' advanceIp
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_ip/{advanceIp}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceIp(
			@PathVariable final Long advanceIp) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceIp(advanceIp)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceM.
	 * @param advanceM the career' advanceM
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_m/{advanceM}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceM(
			@PathVariable final Long advanceM) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceM(advanceM)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceMag.
	 * @param advanceMag the career' advanceMag
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_mag/{advanceMag}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceMag(
			@PathVariable final Long advanceMag) {
		Iterator<WFRPCareerEntity> iter = repository
				.findByAdvanceMag(advanceMag)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceS.
	 * @param advanceS the career' advanceS
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_s/{advanceS}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceS(
			@PathVariable final Long advanceS) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceS(advanceS)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceSb.
	 * @param advanceSb the career' advanceSb
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_sb/{advanceSb}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceSb(
			@PathVariable final Long advanceSb) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceSb(advanceSb)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceT.
	 * @param advanceT the career' advanceT
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_t/{advanceT}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceT(
			@PathVariable final Long advanceT) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceT(advanceT)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceTb.
	 * @param advanceTb the career' advanceTb
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_tb/{advanceTb}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceTb(
			@PathVariable final Long advanceTb) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceTb(advanceTb)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceW.
	 * @param advanceW the career' advanceW
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_w/{advanceW}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceW(
			@PathVariable final Long advanceW) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceW(advanceW)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceWp.
	 * @param advanceWp the career' advanceWp
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_wp/{advanceWp}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceWp(
			@PathVariable final Long advanceWp) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceWp(advanceWp)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a advanceWs.
	 * @param advanceWs the career' advanceWs
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "advance_ws/{advanceWs}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByAdvanceWs(
			@PathVariable final Long advanceWs) {
		Iterator<WFRPCareerEntity> iter = repository.findByAdvanceWs(advanceWs)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a description.
	 * @param description the career' description
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "description/{description}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByDescription(
			@PathVariable final String description) {
		Iterator<WFRPCareerEntity> iter = repository
				.findByDescription(description)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPCareerEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getById(
			@PathVariable final Long id) {
		WFRPCareerEntity entity = repository.findOne(id);
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		resources.add(getCareerResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a saying.
	 * @param saying the career' saying
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "saying/{saying}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getBySaying(
			@PathVariable final String saying) {
		Iterator<WFRPCareerEntity> iter = repository.findBySaying(saying)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a title.
	 * @param title the career' title
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "title/{title}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByTitle(
			@PathVariable final String title) {
		Iterator<WFRPCareerEntity> iter = repository.findByTitle(title)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCareerEntity}s that share a titleFemale.
	 * @param titleFemale the career' titleFemale
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "title_female/{titleFemale}", method = RequestMethod.GET)
	public List<Resource<WFRPCareerEntity>> getByTitleFemale(
			@PathVariable final String titleFemale) {
		Iterator<WFRPCareerEntity> iter = repository
				.findByTitleFemale(titleFemale)
				.iterator();
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		while (iter.hasNext()) {
			resources.add(getCareerResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPCareerEntity}.
	 * @param entity the {@link WFRPCareerEntity}
	 * @return {@link Resource}<{@link WFRPCareerEntity}>
	 */
	private Resource<WFRPCareerEntity> getCareerResource(
			final WFRPCareerEntity entity) {
		Resource<WFRPCareerEntity> resource = new Resource<WFRPCareerEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}
	/**
	 * Saves multiple {@link WFRPCareerEntity}s.
	 * @param entities the list of {@link WFRPCareerEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPCareerEntity>> save(
			@RequestBody final List<WFRPCareerEntity> entities) {
		List<Resource<WFRPCareerEntity>> resources = new ArrayList<Resource<WFRPCareerEntity>>();
		Iterator<WFRPCareerEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPCareerEntity}.
	 * @param entity the {@link WFRPCareerEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPCareerEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPCareerEntity>> save(
			@RequestBody WFRPCareerEntity entity) {
		if (entity.getAllowedGender() != null
				&& entity.getAllowedGender().getId() == null) {
			WFRPGenderEntity allowedGender = null;
			List<Resource<WFRPGenderEntity>> list = null;
			try {
				Method method = WFRPGenderController.class.getDeclaredMethod(
						"getByName", new Class[] { String.class });
				Field field = WFRPGenderEntity.class.getDeclaredField("name");
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity.getAllowedGender()) != null) {
						list = (List<Resource<WFRPGenderEntity>>) method.invoke(
								WFRPGenderController.getInstance(),
								(String) field.get(entity.getAllowedGender()));
					}
				}
				if (list == null) {
					method = WFRPGenderController.class.getDeclaredMethod(
							"getByCode", new Class[] { String.class });
					field = WFRPGenderEntity.class.getDeclaredField(
							"code");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getAllowedGender()) != null) {
							list = (List<Resource<WFRPGenderEntity>>) method
									.invoke(
											WFRPGenderController.getInstance(),
											(String) field.get(
													entity.getAllowedGender()));
						}
					}
				}
				method = null;
				field = null;
			} catch (NoSuchMethodException | SecurityException
					| NoSuchFieldException | IllegalArgumentException
					| IllegalAccessException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			if (list != null
					&& !list.isEmpty()) {
				allowedGender = list.get(0).getContent();
			}
			if (allowedGender == null) {
				allowedGender = (WFRPGenderEntity) ((Resource) WFRPGenderController
						.getInstance().save(
								entity.getAllowedGender())
						.get(0)).getContent();
			}
			entity.setAllowedGender(allowedGender);
			list = null;
		}

		if (entity.getCareerEntries() != null
				&& !entity.getCareerEntries().isEmpty()) {
			for (int i = entity.getCareerEntries().size() - 1; i >= 0; i--) {
				WFRPCareerEntity careerEntries = null;
				List<Resource<WFRPCareerEntity>> list = null;
				try {
					Method method = WFRPCareerController.class
							.getDeclaredMethod(
									"getByName", new Class[] { String.class });
					Field field = WFRPCareerEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(
								entity.getCareerEntries().get(i)) != null) {
							list = (List<Resource<WFRPCareerEntity>>) method
									.invoke(
											WFRPCareerController.getInstance(),
											(String) field.get(
													entity.getCareerEntries()
															.get(i)));
						}
					}
					if (list == null) {
						method = WFRPCareerController.class.getDeclaredMethod(
								"getByCode", new Class[] { String.class });
						field = WFRPCareerEntity.class.getDeclaredField(
								"code");
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getCareerEntries().get(i)) != null) {
								list = (List<Resource<WFRPCareerEntity>>) method
										.invoke(
												WFRPCareerController
														.getInstance(),
												(String) field
														.get(entity
																.getCareerEntries()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (NoSuchMethodException | SecurityException
						| NoSuchFieldException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					careerEntries = list.get(0).getContent();
				}
				if (careerEntries == null) {
					careerEntries = (WFRPCareerEntity) ((Resource) WFRPCareerController
							.getInstance()
							.save(entity.getCareerEntries().get(i)).get(0))
									.getContent();
				}
				entity.getCareerEntries().set(i, careerEntries);
				list = null;
			}
		}

		if (entity.getCareerExits() != null
				&& !entity.getCareerExits().isEmpty()) {
			for (int i = entity.getCareerExits().size() - 1; i >= 0; i--) {
				WFRPCareerEntity careerExits = null;
				List<Resource<WFRPCareerEntity>> list = null;
				try {
					Method method = WFRPCareerController.class
							.getDeclaredMethod(
									"getByName", new Class[] { String.class });
					Field field = WFRPCareerEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getCareerExits().get(i)) != null) {
							list = (List<Resource<WFRPCareerEntity>>) method
									.invoke(
											WFRPCareerController.getInstance(),
											(String) field.get(entity
													.getCareerExits().get(i)));
						}
					}
					if (list == null) {
						method = WFRPCareerController.class.getDeclaredMethod(
								"getByCode", new Class[] { String.class });
						field = WFRPCareerEntity.class.getDeclaredField(
								"code");
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getCareerExits().get(i)) != null) {
								list = (List<Resource<WFRPCareerEntity>>) method
										.invoke(
												WFRPCareerController
														.getInstance(),
												(String) field
														.get(entity
																.getCareerExits()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (NoSuchMethodException | SecurityException
						| NoSuchFieldException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					careerExits = list.get(0).getContent();
				}
				if (careerExits == null) {
					careerExits = (WFRPCareerEntity) ((Resource) WFRPCareerController
							.getInstance()
							.save(entity.getCareerExits().get(i)).get(0))
									.getContent();
				}
				entity.getCareerExits().set(i, careerExits);
				list = null;
			}
		}

		if (entity.getDisallowedRaces() != null
				&& !entity.getDisallowedRaces().isEmpty()) {
			for (int i = entity.getDisallowedRaces().size() - 1; i >= 0; i--) {
				WFRPRaceEntity disallowedRaces = null;
				List<Resource<WFRPRaceEntity>> list = null;
				try {
					Method method = WFRPRaceController.class.getDeclaredMethod(
							"getByName", new Class[] { String.class });
					Field field = WFRPRaceEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(
								entity.getDisallowedRaces().get(i)) != null) {
							list = (List<Resource<WFRPRaceEntity>>) method
									.invoke(
											WFRPRaceController.getInstance(),
											(String) field.get(
													entity.getDisallowedRaces()
															.get(i)));
						}
					}
					if (list == null) {
						method = WFRPRaceController.class.getDeclaredMethod(
								"getByCode", new Class[] { String.class });
						field = WFRPRaceEntity.class.getDeclaredField(
								"code");
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(entity.getDisallowedRaces()
									.get(i)) != null) {
								list = (List<Resource<WFRPRaceEntity>>) method
										.invoke(
												WFRPRaceController
														.getInstance(),
												(String) field
														.get(entity
																.getDisallowedRaces()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (NoSuchMethodException | SecurityException
						| NoSuchFieldException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					disallowedRaces = list.get(0).getContent();
				}
				if (disallowedRaces == null) {
					disallowedRaces = (WFRPRaceEntity) ((Resource) WFRPRaceController
							.getInstance()
							.save(entity.getDisallowedRaces().get(i)).get(0))
									.getContent();
				}
				entity.getDisallowedRaces().set(i, disallowedRaces);
				list = null;
			}
		}

		if (entity.getSkillChoices() != null
				&& !entity.getSkillChoices().isEmpty()) {
			for (int i = entity.getSkillChoices().size() - 1; i >= 0; i--) {
				WFRPSkillChoiceEntity skillChoices = null;
				List<Resource<WFRPSkillChoiceEntity>> list = null;
				try {
					Method method = WFRPSkillChoiceController.class
							.getDeclaredMethod(
									"getByName", new Class[] { String.class });
					Field field = WFRPSkillChoiceEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field
								.get(entity.getSkillChoices().get(i)) != null) {
							list = (List<Resource<WFRPSkillChoiceEntity>>) method
									.invoke(
											WFRPSkillChoiceController
													.getInstance(),
											(String) field.get(entity
													.getSkillChoices().get(i)));
						}
					}
					if (list == null) {
						method = WFRPSkillChoiceController.class
								.getDeclaredMethod(
										"getByCode",
										new Class[] { String.class });
						field = WFRPSkillChoiceEntity.class.getDeclaredField(
								"code");
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getSkillChoices().get(i)) != null) {
								list = (List<Resource<WFRPSkillChoiceEntity>>) method
										.invoke(
												WFRPSkillChoiceController
														.getInstance(),
												(String) field
														.get(entity
																.getSkillChoices()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (NoSuchMethodException | SecurityException
						| NoSuchFieldException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					skillChoices = list.get(0).getContent();
				}
				if (skillChoices == null) {
					System.out.println("could not find skill choice "
							+ entity.getSkillChoices().get(i).getName());
					entity = null;
				}
				entity.getSkillChoices().set(i, skillChoices);
				list = null;
			}
		}

		if (entity.getSkills() != null
				&& !entity.getSkills().isEmpty()) {
			for (int i = entity.getSkills().size() - 1; i >= 0; i--) {
				WFRPSkillEntity skills = null;
				List<Resource<WFRPSkillEntity>> list = null;
				try {
					Method method = WFRPSkillController.class.getDeclaredMethod(
							"getByName", new Class[] { String.class });
					Field field = WFRPSkillEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getSkills().get(i)) != null) {
							list = (List<Resource<WFRPSkillEntity>>) method
									.invoke(
											WFRPSkillController.getInstance(),
											(String) field.get(
													entity.getSkills().get(i)));
						}
					}
					if (list == null) {
						method = WFRPSkillController.class.getDeclaredMethod(
								"getByCode", new Class[] { String.class });
						field = WFRPSkillEntity.class.getDeclaredField(
								"code");
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(entity.getSkills().get(i)) != null) {
								list = (List<Resource<WFRPSkillEntity>>) method
										.invoke(
												WFRPSkillController
														.getInstance(),
												(String) field
														.get(entity.getSkills()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (NoSuchMethodException | SecurityException
						| NoSuchFieldException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					skills = list.get(0).getContent();
				}
				if (skills == null) {
					System.out.println("could not find skill "
							+ entity.getSkills().get(i).getName());
					entity = null;
				}
				entity.getSkills().set(i, skills);
				list = null;
			}
		}

		if (entity.getSourcebook() != null
				&& entity.getSourcebook().getId() == null) {
			WFRPSourcebookEntity sourcebook = null;
			List<Resource<WFRPSourcebookEntity>> list = null;
			try {
				Method method = WFRPSourcebookController.class
						.getDeclaredMethod(
								"getByName", new Class[] { String.class });
				Field field = WFRPSourcebookEntity.class
						.getDeclaredField("name");
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity.getSourcebook()) != null) {
						list = (List<Resource<WFRPSourcebookEntity>>) method
								.invoke(
										WFRPSourcebookController.getInstance(),
										(String) field
												.get(entity.getSourcebook()));
					}
				}
				if (list == null) {
					method = WFRPSourcebookController.class.getDeclaredMethod(
							"getByCode", new Class[] { String.class });
					field = WFRPSourcebookEntity.class.getDeclaredField(
							"code");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getSourcebook()) != null) {
							list = (List<Resource<WFRPSourcebookEntity>>) method
									.invoke(
											WFRPSourcebookController
													.getInstance(),
											(String) field.get(
													entity.getSourcebook()));
						}
					}
				}
				method = null;
				field = null;
			} catch (NoSuchMethodException | SecurityException
					| NoSuchFieldException | IllegalArgumentException
					| IllegalAccessException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			if (list != null
					&& !list.isEmpty()) {
				sourcebook = list.get(0).getContent();
			}
			if (sourcebook == null) {
				System.out.println("could not find sourcebook "
						+ entity.getSourcebook().getName());
				entity = null;
			}
			entity.setSourcebook(sourcebook);
			list = null;
		}

		if (entity.getTalentChoices() != null
				&& !entity.getTalentChoices().isEmpty()) {
			for (int i = entity.getTalentChoices().size() - 1; i >= 0; i--) {
				WFRPTalentChoiceEntity talentChoices = null;
				List<Resource<WFRPTalentChoiceEntity>> list = null;
				try {
					Method method = WFRPTalentChoiceController.class
							.getDeclaredMethod(
									"getByName", new Class[] { String.class });
					Field field = WFRPTalentChoiceEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(
								entity.getTalentChoices().get(i)) != null) {
							list = (List<Resource<WFRPTalentChoiceEntity>>) method
									.invoke(
											WFRPTalentChoiceController
													.getInstance(),
											(String) field.get(
													entity.getTalentChoices()
															.get(i)));
						}
					}
					if (list == null) {
						method = WFRPTalentChoiceController.class
								.getDeclaredMethod(
										"getByCode",
										new Class[] { String.class });
						field = WFRPTalentChoiceEntity.class.getDeclaredField(
								"code");
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getTalentChoices().get(i)) != null) {
								list = (List<Resource<WFRPTalentChoiceEntity>>) method
										.invoke(
												WFRPTalentChoiceController
														.getInstance(),
												(String) field
														.get(entity
																.getTalentChoices()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (NoSuchMethodException | SecurityException
						| NoSuchFieldException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					talentChoices = list.get(0).getContent();
				}
				if (talentChoices == null) {
					System.out.println("could not find talent choice "
							+ entity.getTalentChoices().get(i).getName());
					entity = null;
				}
				entity.getTalentChoices().set(i, talentChoices);
				list = null;
			}
		}

		if (entity.getTalents() != null
				&& !entity.getTalents().isEmpty()) {
			for (int i = entity.getTalents().size() - 1; i >= 0; i--) {
				WFRPTalentEntity talents = null;
				List<Resource<WFRPTalentEntity>> list = null;
				try {
					Method method = WFRPTalentController.class
							.getDeclaredMethod(
									"getByName", new Class[] { String.class });
					Field field = WFRPTalentEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getTalents().get(i)) != null) {
							list = (List<Resource<WFRPTalentEntity>>) method
									.invoke(
											WFRPTalentController.getInstance(),
											(String) field.get(entity
													.getTalents().get(i)));
						}
					}
					if (list == null) {
						method = WFRPTalentController.class.getDeclaredMethod(
								"getByCode", new Class[] { String.class });
						field = WFRPTalentEntity.class.getDeclaredField(
								"code");
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(entity.getTalents().get(i)) != null) {
								list = (List<Resource<WFRPTalentEntity>>) method
										.invoke(
												WFRPTalentController
														.getInstance(),
												(String) field
														.get(entity.getTalents()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (NoSuchMethodException | SecurityException
						| NoSuchFieldException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					talents = list.get(0).getContent();
				}
				if (talents == null) {
					System.out.println("could not find talent "
							+ entity.getTalents().get(i).getName());
					entity = null;
				}
				entity.getTalents().set(i, talents);
				list = null;
			}
		}

		if (entity.getTrappings() != null
				&& !entity.getTrappings().isEmpty()) {
			for (int i = entity.getTrappings().size() - 1; i >= 0; i--) {
				WFRPEquipmentEntity trappings = null;
				List<Resource<WFRPEquipmentEntity>> list = null;
				try {
					Method method = WFRPEquipmentController.class
							.getDeclaredMethod(
									"getByName", new Class[] { String.class });
					Field field = WFRPEquipmentEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getTrappings().get(i)) != null) {
							list = (List<Resource<WFRPEquipmentEntity>>) method
									.invoke(
											WFRPEquipmentController
													.getInstance(),
											(String) field.get(entity
													.getTrappings().get(i)));
						}
					}
					if (list == null) {
						method = WFRPEquipmentController.class
								.getDeclaredMethod(
										"getByCode",
										new Class[] { String.class });
						field = WFRPEquipmentEntity.class.getDeclaredField(
								"code");
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getTrappings().get(i)) != null) {
								list = (List<Resource<WFRPEquipmentEntity>>) method
										.invoke(
												WFRPEquipmentController
														.getInstance(),
												(String) field
														.get(entity
																.getTrappings()
																.get(i)));
							}
						}
					}
					method = null;
					field = null;
				} catch (NoSuchMethodException | SecurityException
						| NoSuchFieldException | IllegalArgumentException
						| IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (list != null
						&& !list.isEmpty()) {
					trappings = list.get(0).getContent();
				}
				if (trappings == null) {
					System.out.println("could not find trappings "
							+ entity.getTrappings().get(i).getName());
					entity = null;
				}
				entity.getTrappings().set(i, trappings);
				list = null;
			}
		}

		if (entity.getType() != null
				&& entity.getType().getId() == null) {
			WFRPCareerTypeEntity type = null;
			List<Resource<WFRPCareerTypeEntity>> list = null;
			try {
				Method method = WFRPCareerTypeController.class
						.getDeclaredMethod(
								"getByName", new Class[] { String.class });
				Field field = WFRPCareerTypeEntity.class
						.getDeclaredField("name");
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity.getType()) != null) {
						list = (List<Resource<WFRPCareerTypeEntity>>) method
								.invoke(
										WFRPCareerTypeController.getInstance(),
										(String) field.get(entity.getType()));
					}
				}
				if (list == null) {
					method = WFRPCareerTypeController.class.getDeclaredMethod(
							"getByCode", new Class[] { String.class });
					field = WFRPCareerTypeEntity.class.getDeclaredField(
							"code");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getType()) != null) {
							list = (List<Resource<WFRPCareerTypeEntity>>) method
									.invoke(
											WFRPCareerTypeController
													.getInstance(),
											(String) field
													.get(entity.getType()));
						}
					}
				}
				method = null;
				field = null;
			} catch (NoSuchMethodException | SecurityException
					| NoSuchFieldException | IllegalArgumentException
					| IllegalAccessException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			if (list != null
					&& !list.isEmpty()) {
				type = list.get(0).getContent();
			}
			if (type == null) {
				System.out.println("could not find career type "
						+ entity.getType().getName());
				entity = null;
			}
			entity.setType(type);
			list = null;
		}

		WFRPCareerEntity savedEntity = repository.save(entity);
		List<Resource<WFRPCareerEntity>> list = getById(savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
