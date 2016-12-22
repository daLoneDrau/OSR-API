package com.osrapi.controllers.wfrp;

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
import com.osrapi.repositories.wfrp.WFRPMeleeWeaponRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/melee_weapons")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPMeleeWeaponController {
	/** the static instance of {@link WFRPMeleeWeaponController}. */
	private static WFRPMeleeWeaponController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPMeleeWeaponController}
	 */
	public static WFRPMeleeWeaponController getInstance() {
		if (instance == null) {
			new WFRPMeleeWeaponController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPMeleeWeaponRepository repository;
	/** Creates a new instance of {@link WFRPMeleeWeaponController}. */
	public WFRPMeleeWeaponController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPMeleeWeaponEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPMeleeWeaponEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPMeleeWeaponEntity>> getAll() {
		Iterator<WFRPMeleeWeaponEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPMeleeWeaponEntity>> resources = new ArrayList<Resource<WFRPMeleeWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getMeleeWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPMeleeWeaponEntity}s that share a dmgModifier.
	 * @param dmgModifier the melee_weapon' dmgModifier
	 * @return {@link List}<{@link Resource}<{@link WFRPMeleeWeaponEntity}>>
	 */
	@RequestMapping(path = "dmg_modifier/{dmgModifier}", method = RequestMethod.GET)
	public List<Resource<WFRPMeleeWeaponEntity>> getByDmgModifier(
			@PathVariable final Long dmgModifier) {
		Iterator<WFRPMeleeWeaponEntity> iter = repository
				.findByDmgModifier(dmgModifier)
				.iterator();
		List<Resource<WFRPMeleeWeaponEntity>> resources = new ArrayList<Resource<WFRPMeleeWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getMeleeWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPMeleeWeaponEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPMeleeWeaponEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPMeleeWeaponEntity>> getById(
			@PathVariable final Long id) {
		WFRPMeleeWeaponEntity entity = repository.findOne(id);
		List<Resource<WFRPMeleeWeaponEntity>> resources = new ArrayList<Resource<WFRPMeleeWeaponEntity>>();
		resources.add(getMeleeWeaponResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPMeleeWeaponEntity}s that share a name.
	 * @param name the melee_weapon' name
	 * @return {@link List}<{@link Resource}<{@link WFRPMeleeWeaponEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPMeleeWeaponEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPMeleeWeaponEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPMeleeWeaponEntity>> resources = new ArrayList<Resource<WFRPMeleeWeaponEntity>>();
		while (iter.hasNext()) {
			resources.add(getMeleeWeaponResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPMeleeWeaponEntity}.
	 * @param entity the {@link WFRPMeleeWeaponEntity}
	 * @return {@link Resource}<{@link WFRPMeleeWeaponEntity}>
	 */
	private Resource<WFRPMeleeWeaponEntity> getMeleeWeaponResource(
			final WFRPMeleeWeaponEntity entity) {
		Resource<WFRPMeleeWeaponEntity> resource = new Resource<WFRPMeleeWeaponEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}

	/**
	 * Saves multiple {@link WFRPMeleeWeaponEntity}s.
	 * @param entities the list of {@link WFRPMeleeWeaponEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPMeleeWeaponEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPMeleeWeaponEntity>> save(
			@RequestBody final List<WFRPMeleeWeaponEntity> entities) {
		List<Resource<WFRPMeleeWeaponEntity>> resources = new ArrayList<Resource<WFRPMeleeWeaponEntity>>();
		Iterator<WFRPMeleeWeaponEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPMeleeWeaponEntity}.
	 * @param entity the {@link WFRPMeleeWeaponEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPMeleeWeaponEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPMeleeWeaponEntity>> save(
			@RequestBody final WFRPMeleeWeaponEntity entity) {

		WFRPMeleeWeaponEntity savedEntity = repository.save(entity);
		List<Resource<WFRPMeleeWeaponEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
