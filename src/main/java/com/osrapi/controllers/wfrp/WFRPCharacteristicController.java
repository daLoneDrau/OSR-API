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

import com.osrapi.models.wfrp.WFRPCharacteristicEntity;
import com.osrapi.repositories.wfrp.WFRPCharacteristicRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/characteristics")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPCharacteristicController {
	/** the static instance of {@link WFRPCharacteristicController}. */
	private static WFRPCharacteristicController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPCharacteristicController}
	 */
	public static WFRPCharacteristicController getInstance() {
		if (instance == null) {
			new WFRPCharacteristicController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPCharacteristicRepository repository;
	/** Creates a new instance of {@link WFRPCharacteristicController}. */
	public WFRPCharacteristicController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPCharacteristicEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPCharacteristicEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPCharacteristicEntity>> getAll() {
		Iterator<WFRPCharacteristicEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPCharacteristicEntity>> resources = new ArrayList<Resource<WFRPCharacteristicEntity>>();
		while (iter.hasNext()) {
			resources.add(getCharacteristicResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCharacteristicEntity}s that share a code.
	 * @param code the characteristic' code
	 * @return {@link List}<{@link Resource}<{@link WFRPCharacteristicEntity}>>
	 */
	@RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	public List<Resource<WFRPCharacteristicEntity>> getByCode(
			@PathVariable final String code) {
		Iterator<WFRPCharacteristicEntity> iter = repository.findByCode(code)
				.iterator();
		List<Resource<WFRPCharacteristicEntity>> resources = new ArrayList<Resource<WFRPCharacteristicEntity>>();
		while (iter.hasNext()) {
			resources.add(getCharacteristicResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCharacteristicEntity}s that share a
	 * description.
	 * @param description the characteristic' description
	 * @return {@link List}<{@link Resource}<{@link WFRPCharacteristicEntity}>>
	 */
	@RequestMapping(path = "description/{description}", method = RequestMethod.GET)
	public List<Resource<WFRPCharacteristicEntity>> getByDescription(
			@PathVariable final String description) {
		Iterator<WFRPCharacteristicEntity> iter = repository
				.findByDescription(description)
				.iterator();
		List<Resource<WFRPCharacteristicEntity>> resources = new ArrayList<Resource<WFRPCharacteristicEntity>>();
		while (iter.hasNext()) {
			resources.add(getCharacteristicResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPCharacteristicEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPCharacteristicEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPCharacteristicEntity>> getById(
			@PathVariable final Long id) {
		WFRPCharacteristicEntity entity = repository.findOne(id);
		List<Resource<WFRPCharacteristicEntity>> resources = new ArrayList<Resource<WFRPCharacteristicEntity>>();
		resources.add(getCharacteristicResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPCharacteristicEntity}s that share a isMain.
	 * @param isMain the characteristic' isMain
	 * @return {@link List}<{@link Resource}<{@link WFRPCharacteristicEntity}>>
	 */
	@RequestMapping(path = "is_main/{isMain}", method = RequestMethod.GET)
	public List<Resource<WFRPCharacteristicEntity>> getByIsMain(
			@PathVariable final Boolean isMain) {
		Iterator<WFRPCharacteristicEntity> iter = repository
				.findByIsMain(isMain)
				.iterator();
		List<Resource<WFRPCharacteristicEntity>> resources = new ArrayList<Resource<WFRPCharacteristicEntity>>();
		while (iter.hasNext()) {
			resources.add(getCharacteristicResource(iter.next()));
		}
		iter = null;
		return resources;
	}

	/**
	 * Gets a list of {@link WFRPCharacteristicEntity}s that share a name.
	 * @param name the characteristic' name
	 * @return {@link List}<{@link Resource}<{@link WFRPCharacteristicEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPCharacteristicEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPCharacteristicEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPCharacteristicEntity>> resources = new ArrayList<Resource<WFRPCharacteristicEntity>>();
		while (iter.hasNext()) {
			resources.add(getCharacteristicResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPCharacteristicEntity}.
	 * @param entity the {@link WFRPCharacteristicEntity}
	 * @return {@link Resource}<{@link WFRPCharacteristicEntity}>
	 */
	private Resource<WFRPCharacteristicEntity> getCharacteristicResource(
			final WFRPCharacteristicEntity entity) {
		Resource<WFRPCharacteristicEntity> resource = new Resource<WFRPCharacteristicEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}
	/**
	 * Saves multiple {@link WFRPCharacteristicEntity}s.
	 * @param entities the list of {@link WFRPCharacteristicEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPCharacteristicEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPCharacteristicEntity>> save(
			@RequestBody final List<WFRPCharacteristicEntity> entities) {
		List<Resource<WFRPCharacteristicEntity>> resources = new ArrayList<Resource<WFRPCharacteristicEntity>>();
		Iterator<WFRPCharacteristicEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPCharacteristicEntity}.
	 * @param entity the {@link WFRPCharacteristicEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPCharacteristicEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPCharacteristicEntity>> save(
			@RequestBody final WFRPCharacteristicEntity entity) {

		WFRPCharacteristicEntity savedEntity = repository.save(entity);
		List<Resource<WFRPCharacteristicEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
