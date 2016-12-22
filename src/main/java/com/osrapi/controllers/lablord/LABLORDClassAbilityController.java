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

import com.osrapi.models.lablord.LABLORDClassAbilityEntity;
import com.osrapi.repositories.lablord.LABLORDClassAbilityRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/class_abilities")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDClassAbilityController {
	/** the static instance of {@link LABLORDClassAbilityController}. */
	private static LABLORDClassAbilityController instance;
	/**
	 * Gets the static instance.
	 * @return {@link LABLORDClassAbilityController}
	 */
	public static LABLORDClassAbilityController getInstance() {
		if (instance == null) {
			new LABLORDClassAbilityController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private LABLORDClassAbilityRepository repository;
	/** Creates a new instance of {@link LABLORDClassAbilityController}. */
	public LABLORDClassAbilityController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link LABLORDClassAbilityEntity}s.
	 * @return {@link List}<{@link Resource}<{@link LABLORDClassAbilityEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<LABLORDClassAbilityEntity>> getAll() {
		Iterator<LABLORDClassAbilityEntity> iter = repository.findAll()
				.iterator();
		List<Resource<LABLORDClassAbilityEntity>> resources = new ArrayList<Resource<LABLORDClassAbilityEntity>>();
		while (iter.hasNext()) {
			resources.add(getClassAbilityResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDClassAbilityEntity}s that share a code.
	 * @param code the class_ability' code
	 * @return {@link List}<{@link Resource}<{@link LABLORDClassAbilityEntity}>>
	 */
	@RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	public List<Resource<LABLORDClassAbilityEntity>> getByCode(
			@PathVariable final Long code) {
		Iterator<LABLORDClassAbilityEntity> iter = repository.findByCode(code)
				.iterator();
		List<Resource<LABLORDClassAbilityEntity>> resources = new ArrayList<Resource<LABLORDClassAbilityEntity>>();
		while (iter.hasNext()) {
			resources.add(getClassAbilityResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDClassAbilityEntity}s that share a
	 * description.
	 * @param description the class_ability' description
	 * @return {@link List}<{@link Resource}<{@link LABLORDClassAbilityEntity}>>
	 */
	@RequestMapping(path = "description/{description}", method = RequestMethod.GET)
	public List<Resource<LABLORDClassAbilityEntity>> getByDescription(
			@PathVariable final String description) {
		Iterator<LABLORDClassAbilityEntity> iter = repository
				.findByDescription(description)
				.iterator();
		List<Resource<LABLORDClassAbilityEntity>> resources = new ArrayList<Resource<LABLORDClassAbilityEntity>>();
		while (iter.hasNext()) {
			resources.add(getClassAbilityResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link LABLORDClassAbilityEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link LABLORDClassAbilityEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<LABLORDClassAbilityEntity>> getById(
			@PathVariable final Long id) {
		LABLORDClassAbilityEntity entity = repository.findOne(id);
		List<Resource<LABLORDClassAbilityEntity>> resources = new ArrayList<Resource<LABLORDClassAbilityEntity>>();
		resources.add(getClassAbilityResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDClassAbilityEntity}s that share a name.
	 * @param name the class_ability' name
	 * @return {@link List}<{@link Resource}<{@link LABLORDClassAbilityEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<LABLORDClassAbilityEntity>> getByName(
			@PathVariable final String name) {
		Iterator<LABLORDClassAbilityEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<LABLORDClassAbilityEntity>> resources = new ArrayList<Resource<LABLORDClassAbilityEntity>>();
		while (iter.hasNext()) {
			resources.add(getClassAbilityResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link LABLORDClassAbilityEntity}.
	 * @param entity the {@link LABLORDClassAbilityEntity}
	 * @return {@link Resource}<{@link LABLORDClassAbilityEntity}>
	 */
	private Resource<LABLORDClassAbilityEntity> getClassAbilityResource(
			final LABLORDClassAbilityEntity entity) {
		Resource<LABLORDClassAbilityEntity> resource = new Resource<LABLORDClassAbilityEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}
	/**
	 * Saves a single {@link LABLORDClassAbilityEntity}.
	 * @param entity the {@link LABLORDClassAbilityEntity} instance
	 * @return {@link List}<{@link Resource}<{@link LABLORDClassAbilityEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<LABLORDClassAbilityEntity>> save(
			@RequestBody final LABLORDClassAbilityEntity entity) {

		LABLORDClassAbilityEntity savedEntity = repository.save(entity);
		List<Resource<LABLORDClassAbilityEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
	/**
	 * Saves multiple {@link LABLORDClassAbilityEntity}s.
	 * @param entities the list of {@link LABLORDClassAbilityEntity} instances
	 * @return {@link List}<{@link Resource}<{@link LABLORDClassAbilityEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<LABLORDClassAbilityEntity>> save(
			@RequestBody final List<LABLORDClassAbilityEntity> entities) {
		List<Resource<LABLORDClassAbilityEntity>> resources = new ArrayList<Resource<LABLORDClassAbilityEntity>>();
		Iterator<LABLORDClassAbilityEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}

	/**
	 * Tries to set the Id for an entity to be saved by locating it in the
	 * repository.
	 * @param entity the {@link LABLORDClassAbilityEntity} instance
	 */
	private void setIdFromRepository(final LABLORDClassAbilityEntity entity) {
		List<LABLORDClassAbilityEntity> old = null;
		try {
			Method method = null;
			Field field = null;
			try {
				method = repository.getClass().getDeclaredMethod(
						"findByName", new Class[] { String.class });
				field = LABLORDClassAbilityEntity.class
						.getDeclaredField("name");
			} catch (NoSuchMethodException | NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (method != null
					&& field != null) {
				field.setAccessible(true);
				if (field.get(entity) != null) {
					old = (List<LABLORDClassAbilityEntity>) method.invoke(
							repository, (String) field.get(entity));
				}
			}
			if (old == null
					|| old != null
							&& old.size() > 1) {
				try {
					method = repository.getClass().getDeclaredMethod(
							"findByCode", new Class[] { String.class });
					field = LABLORDClassAbilityEntity.class.getDeclaredField(
							"code");
				} catch (NoSuchMethodException | NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity) != null) {
						old = (List<LABLORDClassAbilityEntity>) method.invoke(
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
	 * Updates a single {@link LABLORDClassAbilityEntity}.
	 * @param entity the {@link LABLORDClassAbilityEntity} instance
	 * @return {@link List}<{@link Resource}<{@link LABLORDClassAbilityEntity}>>
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public List<Resource<LABLORDClassAbilityEntity>> update(
			@RequestBody final LABLORDClassAbilityEntity entity) {
		if (entity.getId() == null) {
			setIdFromRepository(entity);
		}

		LABLORDClassAbilityEntity savedEntity = repository.save(entity);
		List<Resource<LABLORDClassAbilityEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
	/**
	 * Updates multiple {@link LABLORDClassAbilityEntity}s.
	 * @param entities the list of {@link LABLORDClassAbilityEntity} instances
	 * @return {@link List}<{@link Resource}<{@link LABLORDClassAbilityEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.PUT)
	public List<Resource<LABLORDClassAbilityEntity>> update(
			@RequestBody final List<LABLORDClassAbilityEntity> entities) {
		List<Resource<LABLORDClassAbilityEntity>> resources = new ArrayList<Resource<LABLORDClassAbilityEntity>>();
		Iterator<LABLORDClassAbilityEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(update(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
}
