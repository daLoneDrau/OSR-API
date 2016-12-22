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

import com.osrapi.models.lablord.LABLORDLanguageEntity;
import com.osrapi.repositories.lablord.LABLORDLanguageRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/languages")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDLanguageController {
	/** the static instance of {@link LABLORDLanguageController}. */
	private static LABLORDLanguageController instance;
	/**
	 * Gets the static instance.
	 * @return {@link LABLORDLanguageController}
	 */
	public static LABLORDLanguageController getInstance() {
		if (instance == null) {
			new LABLORDLanguageController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private LABLORDLanguageRepository repository;
	/** Creates a new instance of {@link LABLORDLanguageController}. */
	public LABLORDLanguageController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link LABLORDLanguageEntity}s.
	 * @return {@link List}<{@link Resource}<{@link LABLORDLanguageEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<LABLORDLanguageEntity>> getAll() {
		Iterator<LABLORDLanguageEntity> iter = repository.findAll()
				.iterator();
		List<Resource<LABLORDLanguageEntity>> resources = new ArrayList<Resource<LABLORDLanguageEntity>>();
		while (iter.hasNext()) {
			resources.add(getLanguageResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDLanguageEntity}s that share a code.
	 * @param code the language' code
	 * @return {@link List}<{@link Resource}<{@link LABLORDLanguageEntity}>>
	 */
	@RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	public List<Resource<LABLORDLanguageEntity>> getByCode(
			@PathVariable final Long code) {
		Iterator<LABLORDLanguageEntity> iter = repository.findByCode(code)
				.iterator();
		List<Resource<LABLORDLanguageEntity>> resources = new ArrayList<Resource<LABLORDLanguageEntity>>();
		while (iter.hasNext()) {
			resources.add(getLanguageResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDLanguageEntity}s that share a description.
	 * @param description the language' description
	 * @return {@link List}<{@link Resource}<{@link LABLORDLanguageEntity}>>
	 */
	@RequestMapping(path = "description/{description}", method = RequestMethod.GET)
	public List<Resource<LABLORDLanguageEntity>> getByDescription(
			@PathVariable final String description) {
		Iterator<LABLORDLanguageEntity> iter = repository
				.findByDescription(description)
				.iterator();
		List<Resource<LABLORDLanguageEntity>> resources = new ArrayList<Resource<LABLORDLanguageEntity>>();
		while (iter.hasNext()) {
			resources.add(getLanguageResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link LABLORDLanguageEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link LABLORDLanguageEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<LABLORDLanguageEntity>> getById(
			@PathVariable final Long id) {
		LABLORDLanguageEntity entity = repository.findOne(id);
		List<Resource<LABLORDLanguageEntity>> resources = new ArrayList<Resource<LABLORDLanguageEntity>>();
		resources.add(getLanguageResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDLanguageEntity}s that share a name.
	 * @param name the language' name
	 * @return {@link List}<{@link Resource}<{@link LABLORDLanguageEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<LABLORDLanguageEntity>> getByName(
			@PathVariable final String name) {
		Iterator<LABLORDLanguageEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<LABLORDLanguageEntity>> resources = new ArrayList<Resource<LABLORDLanguageEntity>>();
		while (iter.hasNext()) {
			resources.add(getLanguageResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link LABLORDLanguageEntity}.
	 * @param entity the {@link LABLORDLanguageEntity}
	 * @return {@link Resource}<{@link LABLORDLanguageEntity}>
	 */
	private Resource<LABLORDLanguageEntity> getLanguageResource(
			final LABLORDLanguageEntity entity) {
		Resource<LABLORDLanguageEntity> resource = new Resource<LABLORDLanguageEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}
	/**
	 * Saves a single {@link LABLORDLanguageEntity}.
	 * @param entity the {@link LABLORDLanguageEntity} instance
	 * @return {@link List}<{@link Resource}<{@link LABLORDLanguageEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<LABLORDLanguageEntity>> save(
			@RequestBody final LABLORDLanguageEntity entity) {

		LABLORDLanguageEntity savedEntity = repository.save(entity);
		List<Resource<LABLORDLanguageEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
	/**
	 * Saves multiple {@link LABLORDLanguageEntity}s.
	 * @param entities the list of {@link LABLORDLanguageEntity} instances
	 * @return {@link List}<{@link Resource}<{@link LABLORDLanguageEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<LABLORDLanguageEntity>> save(
			@RequestBody final List<LABLORDLanguageEntity> entities) {
		List<Resource<LABLORDLanguageEntity>> resources = new ArrayList<Resource<LABLORDLanguageEntity>>();
		Iterator<LABLORDLanguageEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}

	/**
	 * Tries to set the Id for an entity to be saved by locating it in the
	 * repository.
	 * @param entity the {@link LABLORDLanguageEntity} instance
	 */
	private void setIdFromRepository(final LABLORDLanguageEntity entity) {
		List<LABLORDLanguageEntity> old = null;
		try {
			Method method = null;
			Field field = null;
			try {
				method = repository.getClass().getDeclaredMethod(
						"findByName", new Class[] { String.class });
				field = LABLORDLanguageEntity.class.getDeclaredField("name");
			} catch (NoSuchMethodException | NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (method != null
					&& field != null) {
				field.setAccessible(true);
				if (field.get(entity) != null) {
					old = (List<LABLORDLanguageEntity>) method.invoke(
							repository, (String) field.get(entity));
				}
			}
			if (old == null
					|| old != null
							&& old.size() > 1) {
				try {
					method = repository.getClass().getDeclaredMethod(
							"findByCode", new Class[] { String.class });
					field = LABLORDLanguageEntity.class.getDeclaredField(
							"code");
				} catch (NoSuchMethodException | NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity) != null) {
						old = (List<LABLORDLanguageEntity>) method.invoke(
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
	 * Updates a single {@link LABLORDLanguageEntity}.
	 * @param entity the {@link LABLORDLanguageEntity} instance
	 * @return {@link List}<{@link Resource}<{@link LABLORDLanguageEntity}>>
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public List<Resource<LABLORDLanguageEntity>> update(
			@RequestBody final LABLORDLanguageEntity entity) {
		if (entity.getId() == null) {
			setIdFromRepository(entity);
		}

		LABLORDLanguageEntity savedEntity = repository.save(entity);
		List<Resource<LABLORDLanguageEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
	/**
	 * Updates multiple {@link LABLORDLanguageEntity}s.
	 * @param entities the list of {@link LABLORDLanguageEntity} instances
	 * @return {@link List}<{@link Resource}<{@link LABLORDLanguageEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.PUT)
	public List<Resource<LABLORDLanguageEntity>> update(
			@RequestBody final List<LABLORDLanguageEntity> entities) {
		List<Resource<LABLORDLanguageEntity>> resources = new ArrayList<Resource<LABLORDLanguageEntity>>();
		Iterator<LABLORDLanguageEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(update(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
}
