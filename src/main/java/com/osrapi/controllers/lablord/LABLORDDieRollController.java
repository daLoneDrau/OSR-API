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

import com.osrapi.models.lablord.LABLORDDieEntity;
import com.osrapi.models.lablord.LABLORDDieRollEntity;
import com.osrapi.repositories.lablord.LABLORDDieRollRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/lablord/die_rolls")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LABLORDDieRollController {
	/** the static instance of {@link LABLORDDieRollController}. */
	private static LABLORDDieRollController instance;
	/**
	 * Gets the static instance.
	 * @return {@link LABLORDDieRollController}
	 */
	public static LABLORDDieRollController getInstance() {
		if (instance == null) {
			new LABLORDDieRollController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private LABLORDDieRollRepository repository;
	/** Creates a new instance of {@link LABLORDDieRollController}. */
	public LABLORDDieRollController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link LABLORDDieRollEntity}s.
	 * @return {@link List}<{@link Resource}<{@link LABLORDDieRollEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<LABLORDDieRollEntity>> getAll() {
		Iterator<LABLORDDieRollEntity> iter = repository.findAll()
				.iterator();
		List<Resource<LABLORDDieRollEntity>> resources = new ArrayList<Resource<LABLORDDieRollEntity>>();
		while (iter.hasNext()) {
			resources.add(getDieRollResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDDieRollEntity}s that share a code.
	 * @param code the die_roll' code
	 * @return {@link List}<{@link Resource}<{@link LABLORDDieRollEntity}>>
	 */
	@RequestMapping(path = "code/{code}", method = RequestMethod.GET)
	public List<Resource<LABLORDDieRollEntity>> getByCode(
			@PathVariable final String code) {
		Iterator<LABLORDDieRollEntity> iter = repository.findByCode(code)
				.iterator();
		List<Resource<LABLORDDieRollEntity>> resources = new ArrayList<Resource<LABLORDDieRollEntity>>();
		while (iter.hasNext()) {
			resources.add(getDieRollResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link LABLORDDieRollEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link LABLORDDieRollEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<LABLORDDieRollEntity>> getById(
			@PathVariable final Long id) {
		LABLORDDieRollEntity entity = repository.findOne(id);
		List<Resource<LABLORDDieRollEntity>> resources = new ArrayList<Resource<LABLORDDieRollEntity>>();
		resources.add(getDieRollResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link LABLORDDieRollEntity}s that share a number.
	 * @param number the die_roll' number
	 * @return {@link List}<{@link Resource}<{@link LABLORDDieRollEntity}>>
	 */
	@RequestMapping(path = "number/{number}", method = RequestMethod.GET)
	public List<Resource<LABLORDDieRollEntity>> getByNumber(
			@PathVariable final Long number) {
		Iterator<LABLORDDieRollEntity> iter = repository.findByNumber(number)
				.iterator();
		List<Resource<LABLORDDieRollEntity>> resources = new ArrayList<Resource<LABLORDDieRollEntity>>();
		while (iter.hasNext()) {
			resources.add(getDieRollResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link LABLORDDieRollEntity}.
	 * @param entity the {@link LABLORDDieRollEntity}
	 * @return {@link Resource}<{@link LABLORDDieRollEntity}>
	 */
	private Resource<LABLORDDieRollEntity> getDieRollResource(
			final LABLORDDieRollEntity entity) {
		Resource<LABLORDDieRollEntity> resource = new Resource<LABLORDDieRollEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}
	/**
	 * Saves a single {@link LABLORDDieRollEntity}.
	 * @param entity the {@link LABLORDDieRollEntity} instance
	 * @return {@link List}<{@link Resource}<{@link LABLORDDieRollEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<LABLORDDieRollEntity>> save(
			@RequestBody final LABLORDDieRollEntity entity) {
		if (entity.getDie() != null
				&& entity.getDie().getId() == null) {
			setDieIdFromRepository(entity);
		}

		LABLORDDieRollEntity savedEntity = repository.save(entity);
		List<Resource<LABLORDDieRollEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
	/**
	 * Saves multiple {@link LABLORDDieRollEntity}s.
	 * @param entities the list of {@link LABLORDDieRollEntity} instances
	 * @return {@link List}<{@link Resource}<{@link LABLORDDieRollEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<LABLORDDieRollEntity>> save(
			@RequestBody final List<LABLORDDieRollEntity> entities) {
		List<Resource<LABLORDDieRollEntity>> resources = new ArrayList<Resource<LABLORDDieRollEntity>>();
		Iterator<LABLORDDieRollEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	private void setDieIdFromRepository(
			final LABLORDDieRollEntity entity) {
		LABLORDDieEntity memberEntity = null;
		List<Resource<LABLORDDieEntity>> list = null;
		try {
			Method method = null;
			Field field = null;
			try {
				method = LABLORDDieController.class.getDeclaredMethod(
						"getByName", new Class[] { String.class });
				field = LABLORDDieEntity.class.getDeclaredField("name");
			} catch (NoSuchMethodException | NoSuchFieldException e) {
			}
			if (method != null
					&& field != null) {
				field.setAccessible(true);
				if (field.get(entity.getDie()) != null) {
					list = (List<Resource<LABLORDDieEntity>>) method
							.invoke(
									LABLORDDieController.getInstance(),
									(String) field
											.get(entity.getDie()));
				}
			}
			if (list == null) {
				try {
					method = LABLORDDieController.class.getDeclaredMethod(
							"getByCode", new Class[] { String.class });
					field = LABLORDDieEntity.class
							.getDeclaredField("code");
				} catch (NoSuchMethodException | NoSuchFieldException e) {
				}
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity.getDie()) != null) {
						list = (List<Resource<LABLORDDieEntity>>) method
								.invoke(LABLORDDieController
										.getInstance(), (String) field.get(
												entity.getDie()));
					}
				}
			}
			method = null;
			field = null;
		} catch (SecurityException | IllegalArgumentException
				| IllegalAccessException
				| InvocationTargetException e) {
		}
		if (list != null
				&& !list.isEmpty()) {
			memberEntity = list.get(0).getContent();
		}
		if (memberEntity == null) {
			memberEntity = (LABLORDDieEntity) ((Resource) LABLORDDieController
					.getInstance().save(
							entity.getDie())
					.get(0)).getContent();
		}
		entity.setDie(memberEntity);
		list = null;
	}

	/**
	 * Tries to set the Id for an entity to be saved by locating it in the
	 * repository.
	 * @param entity the {@link LABLORDDieRollEntity} instance
	 */
	private void setIdFromRepository(final LABLORDDieRollEntity entity) {
		List<LABLORDDieRollEntity> old = null;
		try {
			Method method = null;
			Field field = null;
			try {
				method = repository.getClass().getDeclaredMethod(
						"findByName", new Class[] { String.class });
				field = LABLORDDieRollEntity.class.getDeclaredField("name");
			} catch (NoSuchMethodException | NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (method != null
					&& field != null) {
				field.setAccessible(true);
				if (field.get(entity) != null) {
					old = (List<LABLORDDieRollEntity>) method.invoke(
							repository, (String) field.get(entity));
				}
			}
			if (old == null
					|| old != null
							&& old.size() > 1) {
				try {
					method = repository.getClass().getDeclaredMethod(
							"findByCode", new Class[] { String.class });
					field = LABLORDDieRollEntity.class.getDeclaredField(
							"code");
				} catch (NoSuchMethodException | NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity) != null) {
						old = (List<LABLORDDieRollEntity>) method.invoke(
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
	 * Updates a single {@link LABLORDDieRollEntity}.
	 * @param entity the {@link LABLORDDieRollEntity} instance
	 * @return {@link List}<{@link Resource}<{@link LABLORDDieRollEntity}>>
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public List<Resource<LABLORDDieRollEntity>> update(
			@RequestBody final LABLORDDieRollEntity entity) {
		if (entity.getId() == null) {
			setIdFromRepository(entity);
		}
		if (entity.getDie() != null
				&& entity.getDie().getId() == null) {
			setDieIdFromRepository(entity);
		}

		LABLORDDieRollEntity savedEntity = repository.save(entity);
		List<Resource<LABLORDDieRollEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
	/**
	 * Updates multiple {@link LABLORDDieRollEntity}s.
	 * @param entities the list of {@link LABLORDDieRollEntity} instances
	 * @return {@link List}<{@link Resource}<{@link LABLORDDieRollEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.PUT)
	public List<Resource<LABLORDDieRollEntity>> update(
			@RequestBody final List<LABLORDDieRollEntity> entities) {
		List<Resource<LABLORDDieRollEntity>> resources = new ArrayList<Resource<LABLORDDieRollEntity>>();
		Iterator<LABLORDDieRollEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(update(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
}
