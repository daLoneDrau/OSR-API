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

import com.osrapi.models.wfrp.WFRPTalentEntity;
import com.osrapi.repositories.wfrp.WFRPTalentRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/talents")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPTalentController {
	/** the static instance of {@link WFRPTalentController}. */
	private static WFRPTalentController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPTalentController}
	 */
	public static WFRPTalentController getInstance() {
		if (instance == null) {
			new WFRPTalentController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPTalentRepository repository;
	/** Creates a new instance of {@link WFRPTalentController}. */
	public WFRPTalentController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPTalentEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPTalentEntity>> getAll() {
		Iterator<WFRPTalentEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPTalentEntity>> resources = new ArrayList<Resource<WFRPTalentEntity>>();
		while (iter.hasNext()) {
			resources.add(getTalentResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPTalentEntity}s that share a description.
	 * @param description the talent' description
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentEntity}>>
	 */
	@RequestMapping(path = "description/{description}", method = RequestMethod.GET)
	public List<Resource<WFRPTalentEntity>> getByDescription(
			@PathVariable final String description) {
		Iterator<WFRPTalentEntity> iter = repository
				.findByDescription(description)
				.iterator();
		List<Resource<WFRPTalentEntity>> resources = new ArrayList<Resource<WFRPTalentEntity>>();
		while (iter.hasNext()) {
			resources.add(getTalentResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPTalentEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPTalentEntity>> getById(
			@PathVariable final Long id) {
		WFRPTalentEntity entity = repository.findOne(id);
		List<Resource<WFRPTalentEntity>> resources = new ArrayList<Resource<WFRPTalentEntity>>();
		resources.add(getTalentResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPTalentEntity}s that share a name.
	 * @param name the talent' name
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPTalentEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPTalentEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPTalentEntity>> resources = new ArrayList<Resource<WFRPTalentEntity>>();
		while (iter.hasNext()) {
			resources.add(getTalentResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPTalentEntity}.
	 * @param entity the {@link WFRPTalentEntity}
	 * @return {@link Resource}<{@link WFRPTalentEntity}>
	 */
	private Resource<WFRPTalentEntity> getTalentResource(
			final WFRPTalentEntity entity) {
		Resource<WFRPTalentEntity> resource = new Resource<WFRPTalentEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}

	/**
	 * Saves multiple {@link WFRPTalentEntity}s.
	 * @param entities the list of {@link WFRPTalentEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPTalentEntity>> save(
			@RequestBody final List<WFRPTalentEntity> entities) {
		List<Resource<WFRPTalentEntity>> resources = new ArrayList<Resource<WFRPTalentEntity>>();
		Iterator<WFRPTalentEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPTalentEntity}.
	 * @param entity the {@link WFRPTalentEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPTalentEntity>> save(
			@RequestBody final WFRPTalentEntity entity) {
		if (entity.getPrerequisite() != null
				&& entity.getPrerequisite().getId() == null) {
			WFRPTalentEntity prerequisite = null;
			List<Resource<WFRPTalentEntity>> list = null;
			try {
				Method method = WFRPTalentController.class.getDeclaredMethod(
						"getByName", new Class[] { String.class });
				Field field = WFRPTalentEntity.class.getDeclaredField("name");
				if (method != null
						&& field != null) {
					field.setAccessible(true);
					if (field.get(entity.getPrerequisite()) != null) {
						list = (List<Resource<WFRPTalentEntity>>) method.invoke(
								WFRPTalentController.getInstance(),
								(String) field.get(entity.getPrerequisite()));
					}
				}
				if (list == null) {
					method = WFRPTalentController.class.getDeclaredMethod(
							"getByCode", new Class[] { String.class });
					field = WFRPTalentEntity.class.getDeclaredField(
							"code");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getPrerequisite()) != null) {
							list = (List<Resource<WFRPTalentEntity>>) method
									.invoke(
											WFRPTalentController.getInstance(),
											(String) field.get(
													entity.getPrerequisite()));
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
				prerequisite = list.get(0).getContent();
			}
			if (prerequisite == null) {
				prerequisite = (WFRPTalentEntity) ((Resource) WFRPTalentController
						.getInstance()
						.save(entity.getPrerequisite()).get(0)).getContent();
			}
			entity.setPrerequisite(prerequisite);
			list = null;
		}

		WFRPTalentEntity savedEntity = repository.save(entity);
		List<Resource<WFRPTalentEntity>> list = getById(savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
