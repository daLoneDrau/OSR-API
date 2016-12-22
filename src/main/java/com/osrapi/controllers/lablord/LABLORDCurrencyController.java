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

import com.osrapi.models.lablord.LABLORDCurrencyEntity;
import com.osrapi.repositories.lablord.LABLORDCurrencyRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/currencies")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDCurrencyController {
	/** the static instance of {@link LABLORDCurrencyController}. */
	private static LABLORDCurrencyController instance;
	/**
	 * Gets the static instance.
	 * @return {@link LABLORDCurrencyController}
	 */
	public static LABLORDCurrencyController getInstance() {
		if (instance == null) {
			new LABLORDCurrencyController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private LABLORDCurrencyRepository repository;
	/** Creates a new instance of {@link LABLORDCurrencyController}. */
	public LABLORDCurrencyController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link LABLORDCurrencyEntity}s.
	 * @return {@link List}<{@link Resource}<{@link LABLORDCurrencyEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<LABLORDCurrencyEntity>> getAll() {
		Iterator<LABLORDCurrencyEntity> iter = repository.findAll()
				.iterator();
		List<Resource<LABLORDCurrencyEntity>> resources = new ArrayList<Resource<LABLORDCurrencyEntity>>();
		while (iter.hasNext()) {
			resources.add(getCurrencyResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDCurrencyEntity}s that share a code.
	 * @param code the currency' code
	 * @return {@link List}<{@link Resource}<{@link LABLORDCurrencyEntity}>>
	 */
	@RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	public List<Resource<LABLORDCurrencyEntity>> getByCode(
			@PathVariable final String code) {
		Iterator<LABLORDCurrencyEntity> iter = repository.findByCode(code)
				.iterator();
		List<Resource<LABLORDCurrencyEntity>> resources = new ArrayList<Resource<LABLORDCurrencyEntity>>();
		while (iter.hasNext()) {
			resources.add(getCurrencyResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link LABLORDCurrencyEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link LABLORDCurrencyEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<LABLORDCurrencyEntity>> getById(
			@PathVariable final Long id) {
		LABLORDCurrencyEntity entity = repository.findOne(id);
		List<Resource<LABLORDCurrencyEntity>> resources = new ArrayList<Resource<LABLORDCurrencyEntity>>();
		resources.add(getCurrencyResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDCurrencyEntity}s that share a name.
	 * @param name the currency' name
	 * @return {@link List}<{@link Resource}<{@link LABLORDCurrencyEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<LABLORDCurrencyEntity>> getByName(
			@PathVariable final String name) {
		Iterator<LABLORDCurrencyEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<LABLORDCurrencyEntity>> resources = new ArrayList<Resource<LABLORDCurrencyEntity>>();
		while (iter.hasNext()) {
			resources.add(getCurrencyResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDCurrencyEntity}s that share a sortOrder.
	 * @param sortOrder the currency' sortOrder
	 * @return {@link List}<{@link Resource}<{@link LABLORDCurrencyEntity}>>
	 */
	@RequestMapping(path = "sort_order/{sortOrder}", method = RequestMethod.GET)
	public List<Resource<LABLORDCurrencyEntity>> getBySortOrder(
			@PathVariable final Long sortOrder) {
		Iterator<LABLORDCurrencyEntity> iter = repository
				.findBySortOrder(sortOrder)
				.iterator();
		List<Resource<LABLORDCurrencyEntity>> resources = new ArrayList<Resource<LABLORDCurrencyEntity>>();
		while (iter.hasNext()) {
			resources.add(getCurrencyResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link LABLORDCurrencyEntity}.
	 * @param entity the {@link LABLORDCurrencyEntity}
	 * @return {@link Resource}<{@link LABLORDCurrencyEntity}>
	 */
	private Resource<LABLORDCurrencyEntity> getCurrencyResource(
			final LABLORDCurrencyEntity entity) {
		Resource<LABLORDCurrencyEntity> resource = new Resource<LABLORDCurrencyEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}
	/**
	 * Saves a single {@link LABLORDCurrencyEntity}.
	 * @param entity the {@link LABLORDCurrencyEntity} instance
	 * @return {@link List}<{@link Resource}<{@link LABLORDCurrencyEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<LABLORDCurrencyEntity>> save(
			@RequestBody final LABLORDCurrencyEntity entity) {

		LABLORDCurrencyEntity savedEntity = repository.save(entity);
		List<Resource<LABLORDCurrencyEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
	/**
	 * Saves multiple {@link LABLORDCurrencyEntity}s.
	 * @param entities the list of {@link LABLORDCurrencyEntity} instances
	 * @return {@link List}<{@link Resource}<{@link LABLORDCurrencyEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<LABLORDCurrencyEntity>> save(
			@RequestBody final List<LABLORDCurrencyEntity> entities) {
		List<Resource<LABLORDCurrencyEntity>> resources = new ArrayList<Resource<LABLORDCurrencyEntity>>();
		Iterator<LABLORDCurrencyEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}

	/**
	 * Tries to set the Id for an entity to be saved by locating it in the
	 * repository.
	 * @param entity the {@link LABLORDCurrencyEntity} instance
	 */
	private void setIdFromRepository(final LABLORDCurrencyEntity entity) {
		List<LABLORDCurrencyEntity> old = null;
		try {
			Method method = null;
			Field field = null;
			try {
				method = repository.getClass().getDeclaredMethod(
						"findByName", new Class[] { String.class });
				field = LABLORDCurrencyEntity.class.getDeclaredField("name");
			} catch (NoSuchMethodException | NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (method != null
					&& field != null) {
				field.setAccessible(true);
				if (field.get(entity) != null) {
					old = (List<LABLORDCurrencyEntity>) method.invoke(
							repository, (String) field.get(entity));
				}
			}
			if (old == null
					|| old != null
							&& old.size() > 1) {
				try {
					method = repository.getClass().getDeclaredMethod(
							"findByCode", new Class[] { String.class });
					field = LABLORDCurrencyEntity.class.getDeclaredField(
							"code");
				} catch (NoSuchMethodException | NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity) != null) {
						old = (List<LABLORDCurrencyEntity>) method.invoke(
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
	 * Updates a single {@link LABLORDCurrencyEntity}.
	 * @param entity the {@link LABLORDCurrencyEntity} instance
	 * @return {@link List}<{@link Resource}<{@link LABLORDCurrencyEntity}>>
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public List<Resource<LABLORDCurrencyEntity>> update(
			@RequestBody final LABLORDCurrencyEntity entity) {
		if (entity.getId() == null) {
			setIdFromRepository(entity);
		}

		LABLORDCurrencyEntity savedEntity = repository.save(entity);
		List<Resource<LABLORDCurrencyEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
	/**
	 * Updates multiple {@link LABLORDCurrencyEntity}s.
	 * @param entities the list of {@link LABLORDCurrencyEntity} instances
	 * @return {@link List}<{@link Resource}<{@link LABLORDCurrencyEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.PUT)
	public List<Resource<LABLORDCurrencyEntity>> update(
			@RequestBody final List<LABLORDCurrencyEntity> entities) {
		List<Resource<LABLORDCurrencyEntity>> resources = new ArrayList<Resource<LABLORDCurrencyEntity>>();
		Iterator<LABLORDCurrencyEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(update(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
}
