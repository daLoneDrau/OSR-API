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

import com.osrapi.models.wfrp.WFRPWeaponQualityEntity;
import com.osrapi.repositories.wfrp.WFRPWeaponQualityRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/weapon_qualitys")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPWeaponQualityController {
	/** the static instance of {@link WFRPWeaponQualityController}. */
	private static WFRPWeaponQualityController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPWeaponQualityController}
	 */
	public static WFRPWeaponQualityController getInstance() {
		if (instance == null) {
			new WFRPWeaponQualityController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPWeaponQualityRepository repository;
	/** Creates a new instance of {@link WFRPWeaponQualityController}. */
	public WFRPWeaponQualityController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPWeaponQualityEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponQualityEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPWeaponQualityEntity>> getAll() {
		Iterator<WFRPWeaponQualityEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPWeaponQualityEntity>> resources = new ArrayList<Resource<WFRPWeaponQualityEntity>>();
		while (iter.hasNext()) {
			resources.add(getWeaponQualityResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPWeaponQualityEntity}s that share a code.
	 * @param code the weapon_quality' code
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponQualityEntity}>>
	 */
	@RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	public List<Resource<WFRPWeaponQualityEntity>> getByCode(
			@PathVariable final Long code) {
		Iterator<WFRPWeaponQualityEntity> iter = repository.findByCode(code)
				.iterator();
		List<Resource<WFRPWeaponQualityEntity>> resources = new ArrayList<Resource<WFRPWeaponQualityEntity>>();
		while (iter.hasNext()) {
			resources.add(getWeaponQualityResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPWeaponQualityEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponQualityEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPWeaponQualityEntity>> getById(
			@PathVariable final Long id) {
		WFRPWeaponQualityEntity entity = repository.findOne(id);
		List<Resource<WFRPWeaponQualityEntity>> resources = new ArrayList<Resource<WFRPWeaponQualityEntity>>();
		resources.add(getWeaponQualityResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPWeaponQualityEntity}s that share a name.
	 * @param name the weapon_quality' name
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponQualityEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPWeaponQualityEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPWeaponQualityEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPWeaponQualityEntity>> resources = new ArrayList<Resource<WFRPWeaponQualityEntity>>();
		while (iter.hasNext()) {
			resources.add(getWeaponQualityResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPWeaponQualityEntity}.
	 * @param entity the {@link WFRPWeaponQualityEntity}
	 * @return {@link Resource}<{@link WFRPWeaponQualityEntity}>
	 */
	private Resource<WFRPWeaponQualityEntity> getWeaponQualityResource(
			final WFRPWeaponQualityEntity entity) {
		Resource<WFRPWeaponQualityEntity> resource = new Resource<WFRPWeaponQualityEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}

	/**
	 * Saves multiple {@link WFRPWeaponQualityEntity}s.
	 * @param entities the list of {@link WFRPWeaponQualityEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponQualityEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPWeaponQualityEntity>> save(
			@RequestBody final List<WFRPWeaponQualityEntity> entities) {
		List<Resource<WFRPWeaponQualityEntity>> resources = new ArrayList<Resource<WFRPWeaponQualityEntity>>();
		Iterator<WFRPWeaponQualityEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPWeaponQualityEntity}.
	 * @param entity the {@link WFRPWeaponQualityEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPWeaponQualityEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPWeaponQualityEntity>> save(
			@RequestBody final WFRPWeaponQualityEntity entity) {

		WFRPWeaponQualityEntity savedEntity = repository.save(entity);
		List<Resource<WFRPWeaponQualityEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
