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

import com.osrapi.models.wfrp.WFRPArmourCoverageEntity;
import com.osrapi.repositories.wfrp.WFRPArmourCoverageRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/armour_coverages")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPArmourCoverageController {
	/** the static instance of {@link WFRPArmourCoverageController}. */
	private static WFRPArmourCoverageController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPArmourCoverageController}
	 */
	public static WFRPArmourCoverageController getInstance() {
		if (instance == null) {
			new WFRPArmourCoverageController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPArmourCoverageRepository repository;
	/** Creates a new instance of {@link WFRPArmourCoverageController}. */
	public WFRPArmourCoverageController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPArmourCoverageEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourCoverageEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPArmourCoverageEntity>> getAll() {
		Iterator<WFRPArmourCoverageEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPArmourCoverageEntity>> resources = new ArrayList<Resource<WFRPArmourCoverageEntity>>();
		while (iter.hasNext()) {
			resources.add(getArmourCoverageResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPArmourCoverageEntity}.
	 * @param entity the {@link WFRPArmourCoverageEntity}
	 * @return {@link Resource}<{@link WFRPArmourCoverageEntity}>
	 */
	private Resource<WFRPArmourCoverageEntity> getArmourCoverageResource(
			final WFRPArmourCoverageEntity entity) {
		Resource<WFRPArmourCoverageEntity> resource = new Resource<WFRPArmourCoverageEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}
	/**
	 * Gets a list of {@link WFRPArmourCoverageEntity}s that share a flag.
	 * @param flag the armour_coverage' flag
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourCoverageEntity}>>
	 */
	@RequestMapping(path = "flag/{flag}", method = RequestMethod.GET)
	public List<Resource<WFRPArmourCoverageEntity>> getByFlag(
			@PathVariable final Long flag) {
		Iterator<WFRPArmourCoverageEntity> iter = repository.findByFlag(flag)
				.iterator();
		List<Resource<WFRPArmourCoverageEntity>> resources = new ArrayList<Resource<WFRPArmourCoverageEntity>>();
		while (iter.hasNext()) {
			resources.add(getArmourCoverageResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPArmourCoverageEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourCoverageEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPArmourCoverageEntity>> getById(
			@PathVariable final Long id) {
		WFRPArmourCoverageEntity entity = repository.findOne(id);
		List<Resource<WFRPArmourCoverageEntity>> resources = new ArrayList<Resource<WFRPArmourCoverageEntity>>();
		resources.add(getArmourCoverageResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPArmourCoverageEntity}s that share a name.
	 * @param name the armour_coverage' name
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourCoverageEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPArmourCoverageEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPArmourCoverageEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPArmourCoverageEntity>> resources = new ArrayList<Resource<WFRPArmourCoverageEntity>>();
		while (iter.hasNext()) {
			resources.add(getArmourCoverageResource(iter.next()));
		}
		iter = null;
		return resources;
	}

	/**
	 * Saves multiple {@link WFRPArmourCoverageEntity}s.
	 * @param entities the list of {@link WFRPArmourCoverageEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourCoverageEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPArmourCoverageEntity>> save(
			@RequestBody final List<WFRPArmourCoverageEntity> entities) {
		List<Resource<WFRPArmourCoverageEntity>> resources = new ArrayList<Resource<WFRPArmourCoverageEntity>>();
		Iterator<WFRPArmourCoverageEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPArmourCoverageEntity}.
	 * @param entity the {@link WFRPArmourCoverageEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPArmourCoverageEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPArmourCoverageEntity>> save(
			@RequestBody final WFRPArmourCoverageEntity entity) {

		WFRPArmourCoverageEntity savedEntity = repository.save(entity);
		List<Resource<WFRPArmourCoverageEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
