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

import com.osrapi.models.wfrp.WFRPMissileWeaponEntity;
import com.osrapi.models.wfrp.WFRPWeaponReloadEntity;
import com.osrapi.repositories.wfrp.WFRPMissileWeaponRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/missile_weapons")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPMissileWeaponController {
	/** the static instance of {@link WFRPMissileWeaponController}. */
	private static WFRPMissileWeaponController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPMissileWeaponController}
	 */
	public static WFRPMissileWeaponController getInstance() {
		if (instance == null) {
			new WFRPMissileWeaponController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPMissileWeaponRepository repository;
	/** Creates a new instance of {@link WFRPMissileWeaponController}. */
	public WFRPMissileWeaponController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPMissileWeaponEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPMissileWeaponEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPMissileWeaponEntity>> getAll() {
		Iterator<WFRPMissileWeaponEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPMissileWeaponEntity>> resources = new ArrayList<Resource<WFRPMissileWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getMissileWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPMissileWeaponEntity}s that share a dmg.
	 * @param dmg the missile_weapon' dmg
	 * @return {@link List}<{@link Resource}<{@link WFRPMissileWeaponEntity}>>
	 */
	@RequestMapping(path = "dmg/{dmg}", method = RequestMethod.GET)
	public List<Resource<WFRPMissileWeaponEntity>> getByDmg(
			@PathVariable final Long dmg) {
		Iterator<WFRPMissileWeaponEntity> iter = repository.findByDmg(dmg)
				.iterator();
		List<Resource<WFRPMissileWeaponEntity>> resources = new ArrayList<Resource<WFRPMissileWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getMissileWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPMissileWeaponEntity}s that share a dmgIsSb.
	 * @param dmgIsSb the missile_weapon' dmgIsSb
	 * @return {@link List}<{@link Resource}<{@link WFRPMissileWeaponEntity}>>
	 */
	@RequestMapping(path = "dmg_is_sb/{dmgIsSb}", method = RequestMethod.GET)
	public List<Resource<WFRPMissileWeaponEntity>> getByDmgIsSb(
			@PathVariable final Boolean dmgIsSb) {
		Iterator<WFRPMissileWeaponEntity> iter = repository
				.findByDmgIsSb(dmgIsSb)
				.iterator();
		List<Resource<WFRPMissileWeaponEntity>> resources = new ArrayList<Resource<WFRPMissileWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getMissileWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPMissileWeaponEntity}s that share a dmgModifier.
	 * @param dmgModifier the missile_weapon' dmgModifier
	 * @return {@link List}<{@link Resource}<{@link WFRPMissileWeaponEntity}>>
	 */
	@RequestMapping(path = "dmg_modifier/{dmgModifier}", method = RequestMethod.GET)
	public List<Resource<WFRPMissileWeaponEntity>> getByDmgModifier(
			@PathVariable final Long dmgModifier) {
		Iterator<WFRPMissileWeaponEntity> iter = repository
				.findByDmgModifier(dmgModifier)
				.iterator();
		List<Resource<WFRPMissileWeaponEntity>> resources = new ArrayList<Resource<WFRPMissileWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getMissileWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPMissileWeaponEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPMissileWeaponEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPMissileWeaponEntity>> getById(
			@PathVariable final Long id) {
		WFRPMissileWeaponEntity entity = repository.findOne(id);
		List<Resource<WFRPMissileWeaponEntity>> resources = new ArrayList<Resource<WFRPMissileWeaponEntity>>();
		resources.add(getMissileWeaponResource(entity));
		entity = null;
		return resources;
	}

	/**
	 * Gets a list of {@link WFRPMissileWeaponEntity}s that share a longRange.
	 * @param longRange the missile_weapon' longRange
	 * @return {@link List}<{@link Resource}<{@link WFRPMissileWeaponEntity}>>
	 */
	@RequestMapping(path = "long_range/{longRange}", method = RequestMethod.GET)
	public List<Resource<WFRPMissileWeaponEntity>> getByLongRange(
			@PathVariable final Long longRange) {
		Iterator<WFRPMissileWeaponEntity> iter = repository
				.findByLongRange(longRange)
				.iterator();
		List<Resource<WFRPMissileWeaponEntity>> resources = new ArrayList<Resource<WFRPMissileWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getMissileWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPMissileWeaponEntity}s that share a name.
	 * @param name the missile_weapon' name
	 * @return {@link List}<{@link Resource}<{@link WFRPMissileWeaponEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPMissileWeaponEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPMissileWeaponEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPMissileWeaponEntity>> resources = new ArrayList<Resource<WFRPMissileWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getMissileWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPMissileWeaponEntity}s that share a shortRange.
	 * @param shortRange the missile_weapon' shortRange
	 * @return {@link List}<{@link Resource}<{@link WFRPMissileWeaponEntity}>>
	 */
	@RequestMapping(path = "short_range/{shortRange}", method = RequestMethod.GET)
	public List<Resource<WFRPMissileWeaponEntity>> getByShortRange(
			@PathVariable final Long shortRange) {
		Iterator<WFRPMissileWeaponEntity> iter = repository
				.findByShortRange(shortRange)
				.iterator();
		List<Resource<WFRPMissileWeaponEntity>> resources = new ArrayList<Resource<WFRPMissileWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getMissileWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPMissileWeaponEntity}.
	 * @param entity the {@link WFRPMissileWeaponEntity}
	 * @return {@link Resource}<{@link WFRPMissileWeaponEntity}>
	 */
	private Resource<WFRPMissileWeaponEntity> getMissileWeaponResource(
			final WFRPMissileWeaponEntity entity) {
		Resource<WFRPMissileWeaponEntity> resource = new Resource<WFRPMissileWeaponEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}
	/**
	 * Saves multiple {@link WFRPMissileWeaponEntity}s.
	 * @param entities the list of {@link WFRPMissileWeaponEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPMissileWeaponEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPMissileWeaponEntity>> save(
			@RequestBody final List<WFRPMissileWeaponEntity> entities) {
		List<Resource<WFRPMissileWeaponEntity>> resources = new ArrayList<Resource<WFRPMissileWeaponEntity>>();
		Iterator<WFRPMissileWeaponEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPMissileWeaponEntity}.
	 * @param entity the {@link WFRPMissileWeaponEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPMissileWeaponEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPMissileWeaponEntity>> save(
			@RequestBody final WFRPMissileWeaponEntity entity) {
		if (entity.getReload() != null
				&& entity.getReload().getId() == null) {
			WFRPWeaponReloadEntity reload = null;
			List<Resource<WFRPWeaponReloadEntity>> list = null;
			try {
				Method method = WFRPWeaponReloadController.class
						.getDeclaredMethod(
								"getByName", new Class[] { String.class });
				Field field = WFRPWeaponReloadEntity.class
						.getDeclaredField("name");
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity.getReload()) != null) {
						list = (List<Resource<WFRPWeaponReloadEntity>>) method
								.invoke(
										WFRPWeaponReloadController
												.getInstance(),
										(String) field.get(entity.getReload()));
					}
				}
				if (list == null) {
					method = WFRPWeaponReloadController.class.getDeclaredMethod(
							"getByCode", new Class[] { String.class });
					field = WFRPWeaponReloadEntity.class.getDeclaredField(
							"code");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getReload()) != null) {
							list = (List<Resource<WFRPWeaponReloadEntity>>) method
									.invoke(
											WFRPWeaponReloadController
													.getInstance(),
											(String) field
													.get(entity.getReload()));
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
				reload = list.get(0).getContent();
			}
			if (reload == null) {
				reload = (WFRPWeaponReloadEntity) ((Resource) WFRPWeaponReloadController
						.getInstance()
						.save(entity.getReload()).get(0)).getContent();
			}
			entity.setReload(reload);
			list = null;
		}

		WFRPMissileWeaponEntity savedEntity = repository.save(entity);
		List<Resource<WFRPMissileWeaponEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
