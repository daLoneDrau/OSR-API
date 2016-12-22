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

import com.osrapi.models.wfrp.WFRPMeleeWeaponEntity;
import com.osrapi.models.wfrp.WFRPMissileWeaponEntity;
import com.osrapi.models.wfrp.WFRPWeaponEntity;
import com.osrapi.models.wfrp.WFRPWeaponGroupEntity;
import com.osrapi.models.wfrp.WFRPWeaponQualityEntity;
import com.osrapi.repositories.wfrp.WFRPWeaponRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/weapons")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPWeaponController {
	/** the static instance of {@link WFRPWeaponController}. */
	private static WFRPWeaponController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPWeaponController}
	 */
	public static WFRPWeaponController getInstance() {
		if (instance == null) {
			new WFRPWeaponController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPWeaponRepository repository;
	/** Creates a new instance of {@link WFRPWeaponController}. */
	public WFRPWeaponController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPWeaponEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPWeaponEntity>> getAll() {
		Iterator<WFRPWeaponEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPWeaponEntity>> resources = new ArrayList<Resource<WFRPWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPWeaponEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPWeaponEntity>> getById(
			@PathVariable final Long id) {
		WFRPWeaponEntity entity = repository.findOne(id);
		List<Resource<WFRPWeaponEntity>> resources = new ArrayList<Resource<WFRPWeaponEntity>>();
		resources.add(getWeaponResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPWeaponEntity}s that share a name.
	 * @param name the weapon' name
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPWeaponEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPWeaponEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPWeaponEntity>> resources = new ArrayList<Resource<WFRPWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPWeaponEntity}s that share a twoHanded.
	 * @param twoHanded the weapon' twoHanded
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponEntity}>>
	 */
	@RequestMapping(path = "two_handed/{twoHanded}", method = RequestMethod.GET)
	public List<Resource<WFRPWeaponEntity>> getByTwoHanded(
			@PathVariable final Boolean twoHanded) {
		Iterator<WFRPWeaponEntity> iter = repository.findByTwoHanded(twoHanded)
				.iterator();
		List<Resource<WFRPWeaponEntity>> resources = new ArrayList<Resource<WFRPWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPWeaponEntity}.
	 * @param entity the {@link WFRPWeaponEntity}
	 * @return {@link Resource}<{@link WFRPWeaponEntity}>
	 */
	private Resource<WFRPWeaponEntity> getWeaponResource(
			final WFRPWeaponEntity entity) {
		Resource<WFRPWeaponEntity> resource = new Resource<WFRPWeaponEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}

	/**
	 * Saves multiple {@link WFRPWeaponEntity}s.
	 * @param entities the list of {@link WFRPWeaponEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPWeaponEntity>> save(
			@RequestBody final List<WFRPWeaponEntity> entities) {
		List<Resource<WFRPWeaponEntity>> resources = new ArrayList<Resource<WFRPWeaponEntity>>();
		Iterator<WFRPWeaponEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPWeaponEntity}.
	 * @param entity the {@link WFRPWeaponEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPWeaponEntity>> save(
			@RequestBody final WFRPWeaponEntity entity) {
		if (entity.getMeleeWeaponData() != null
				&& entity.getMeleeWeaponData().getId() == null) {
			WFRPMeleeWeaponEntity meleeWeaponData = null;
			List<Resource<WFRPMeleeWeaponEntity>> list = null;
			try {
				Method method = WFRPMeleeWeaponController.class
						.getDeclaredMethod(
								"getByName", new Class[] { String.class });
				Field field = WFRPMeleeWeaponEntity.class
						.getDeclaredField("name");
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity.getMeleeWeaponData()) != null) {
						list = (List<Resource<WFRPMeleeWeaponEntity>>) method
								.invoke(
										WFRPMeleeWeaponController.getInstance(),
										(String) field.get(
												entity.getMeleeWeaponData()));
					}
				}
				if (list == null) {
					method = WFRPMeleeWeaponController.class.getDeclaredMethod(
							"getByCode", new Class[] { String.class });
					field = WFRPMeleeWeaponEntity.class.getDeclaredField(
							"code");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getMeleeWeaponData()) != null) {
							list = (List<Resource<WFRPMeleeWeaponEntity>>) method
									.invoke(
											WFRPMeleeWeaponController
													.getInstance(),
											(String) field.get(entity
													.getMeleeWeaponData()));
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
				meleeWeaponData = list.get(0).getContent();
			}
			if (meleeWeaponData == null) {
				meleeWeaponData = (WFRPMeleeWeaponEntity) ((Resource) WFRPMeleeWeaponController
						.getInstance()
						.save(entity.getMeleeWeaponData()).get(0)).getContent();
			}
			entity.setMeleeWeaponData(meleeWeaponData);
			list = null;
		}

		if (entity.getMissileWeaponData() != null
				&& entity.getMissileWeaponData().getId() == null) {
			WFRPMissileWeaponEntity missileWeaponData = null;
			List<Resource<WFRPMissileWeaponEntity>> list = null;
			try {
				Method method = WFRPMissileWeaponController.class
						.getDeclaredMethod(
								"getByName", new Class[] { String.class });
				Field field = WFRPMissileWeaponEntity.class
						.getDeclaredField("name");
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity.getMissileWeaponData()) != null) {
						list = (List<Resource<WFRPMissileWeaponEntity>>) method
								.invoke(
										WFRPMissileWeaponController
												.getInstance(),
										(String) field.get(
												entity.getMissileWeaponData()));
					}
				}
				if (list == null) {
					method = WFRPMissileWeaponController.class
							.getDeclaredMethod(
									"getByCode", new Class[] { String.class });
					field = WFRPMissileWeaponEntity.class.getDeclaredField(
							"code");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getMissileWeaponData()) != null) {
							list = (List<Resource<WFRPMissileWeaponEntity>>) method
									.invoke(
											WFRPMissileWeaponController
													.getInstance(),
											(String) field.get(entity
													.getMissileWeaponData()));
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
				missileWeaponData = list.get(0).getContent();
			}
			if (missileWeaponData == null) {
				missileWeaponData = (WFRPMissileWeaponEntity) ((Resource) WFRPMissileWeaponController
						.getInstance()
						.save(entity.getMissileWeaponData()).get(0))
								.getContent();
			}
			entity.setMissileWeaponData(missileWeaponData);
			list = null;
		}

		if (entity.getQualities() != null
				&& !entity.getQualities().isEmpty()) {
			for (int i = entity.getQualities().size() - 1; i >= 0; i--) {
				WFRPWeaponQualityEntity qualities = null;
				List<Resource<WFRPWeaponQualityEntity>> list = null;
				try {
					Method method = WFRPWeaponQualityController.class
							.getDeclaredMethod(
									"getByName", new Class[] { String.class });
					Field field = WFRPWeaponQualityEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getQualities().get(i)) != null) {
							list = (List<Resource<WFRPWeaponQualityEntity>>) method
									.invoke(
											WFRPWeaponQualityController
													.getInstance(),
											(String) field.get(entity
													.getQualities().get(i)));
						}
					}
					if (list == null) {
						method = WFRPWeaponQualityController.class
								.getDeclaredMethod(
										"getByCode",
										new Class[] { String.class });
						field = WFRPWeaponQualityEntity.class.getDeclaredField(
								"code");
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(
									entity.getQualities().get(i)) != null) {
								list = (List<Resource<WFRPWeaponQualityEntity>>) method
										.invoke(
												WFRPWeaponQualityController
														.getInstance(),
												(String) field
														.get(entity
																.getQualities()
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
					qualities = list.get(0).getContent();
				}
				if (qualities == null) {
					qualities = (WFRPWeaponQualityEntity) ((Resource) WFRPWeaponQualityController
							.getInstance()
							.save(entity.getQualities().get(i)).get(0))
									.getContent();
				}
				entity.getQualities().set(i, qualities);
				list = null;
			}
		}

		if (entity.getWeaponGroup() != null
				&& entity.getWeaponGroup().getId() == null) {
			WFRPWeaponGroupEntity weaponGroup = null;
			List<Resource<WFRPWeaponGroupEntity>> list = null;
			try {
				Method method = WFRPWeaponGroupController.class
						.getDeclaredMethod(
								"getByName", new Class[] { String.class });
				Field field = WFRPWeaponGroupEntity.class
						.getDeclaredField("name");
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity.getWeaponGroup()) != null) {
						list = (List<Resource<WFRPWeaponGroupEntity>>) method
								.invoke(
										WFRPWeaponGroupController.getInstance(),
										(String) field
												.get(entity.getWeaponGroup()));
					}
				}
				if (list == null) {
					method = WFRPWeaponGroupController.class.getDeclaredMethod(
							"getByCode", new Class[] { String.class });
					field = WFRPWeaponGroupEntity.class.getDeclaredField(
							"code");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getWeaponGroup()) != null) {
							list = (List<Resource<WFRPWeaponGroupEntity>>) method
									.invoke(
											WFRPWeaponGroupController
													.getInstance(),
											(String) field.get(
													entity.getWeaponGroup()));
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
				weaponGroup = list.get(0).getContent();
			}
			if (weaponGroup == null) {
				weaponGroup = (WFRPWeaponGroupEntity) ((Resource) WFRPWeaponGroupController
						.getInstance()
						.save(entity.getWeaponGroup()).get(0)).getContent();
			}
			entity.setWeaponGroup(weaponGroup);
			list = null;
		}

		WFRPWeaponEntity savedEntity = repository.save(entity);
		List<Resource<WFRPWeaponEntity>> list = getById(savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
