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

import com.osrapi.models.wfrp.WFRPTalentChoiceEntity;
import com.osrapi.models.wfrp.WFRPTalentEntity;
import com.osrapi.repositories.wfrp.WFRPTalentChoiceRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/talent_choices")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPTalentChoiceController {
	/** the static instance of {@link WFRPTalentChoiceController}. */
	private static WFRPTalentChoiceController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPTalentChoiceController}
	 */
	public static WFRPTalentChoiceController getInstance() {
		if (instance == null) {
			new WFRPTalentChoiceController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPTalentChoiceRepository repository;
	/** Creates a new instance of {@link WFRPTalentChoiceController}. */
	public WFRPTalentChoiceController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPTalentChoiceEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentChoiceEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPTalentChoiceEntity>> getAll() {
		Iterator<WFRPTalentChoiceEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPTalentChoiceEntity>> resources = new ArrayList<Resource<WFRPTalentChoiceEntity>>();
		while (iter.hasNext()) {
			resources.add(getTalentChoiceResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPTalentChoiceEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentChoiceEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPTalentChoiceEntity>> getById(
			@PathVariable final Long id) {
		WFRPTalentChoiceEntity entity = repository.findOne(id);
		List<Resource<WFRPTalentChoiceEntity>> resources = new ArrayList<Resource<WFRPTalentChoiceEntity>>();
		resources.add(getTalentChoiceResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPTalentChoiceEntity}s that share a name.
	 * @param name the talent_choice' name
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentChoiceEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPTalentChoiceEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPTalentChoiceEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPTalentChoiceEntity>> resources = new ArrayList<Resource<WFRPTalentChoiceEntity>>();
		while (iter.hasNext()) {
			resources.add(getTalentChoiceResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPTalentChoiceEntity}s that share a numToChoose.
	 * @param numToChoose the talent_choice' numToChoose
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentChoiceEntity}>>
	 */
	@RequestMapping(path = "num_to_choose/{numToChoose}", method = RequestMethod.GET)
	public List<Resource<WFRPTalentChoiceEntity>> getByNumToChoose(
			@PathVariable final Long numToChoose) {
		Iterator<WFRPTalentChoiceEntity> iter = repository
				.findByNumToChoose(numToChoose)
				.iterator();
		List<Resource<WFRPTalentChoiceEntity>> resources = new ArrayList<Resource<WFRPTalentChoiceEntity>>();
		while (iter.hasNext()) {
			resources.add(getTalentChoiceResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPTalentChoiceEntity}.
	 * @param entity the {@link WFRPTalentChoiceEntity}
	 * @return {@link Resource}<{@link WFRPTalentChoiceEntity}>
	 */
	private Resource<WFRPTalentChoiceEntity> getTalentChoiceResource(
			final WFRPTalentChoiceEntity entity) {
		Resource<WFRPTalentChoiceEntity> resource = new Resource<WFRPTalentChoiceEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}

	/**
	 * Saves multiple {@link WFRPTalentChoiceEntity}s.
	 * @param entities the list of {@link WFRPTalentChoiceEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentChoiceEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPTalentChoiceEntity>> save(
			@RequestBody final List<WFRPTalentChoiceEntity> entities) {
		List<Resource<WFRPTalentChoiceEntity>> resources = new ArrayList<Resource<WFRPTalentChoiceEntity>>();
		Iterator<WFRPTalentChoiceEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPTalentChoiceEntity}.
	 * @param entity the {@link WFRPTalentChoiceEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPTalentChoiceEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPTalentChoiceEntity>> save(
			@RequestBody final WFRPTalentChoiceEntity entity) {
		if (entity.getTalents() != null
				&& !entity.getTalents().isEmpty()) {
			for (int i = entity.getTalents().size() - 1; i >= 0; i--) {
				WFRPTalentEntity talents = null;
				List<Resource<WFRPTalentEntity>> list = null;
				try {
					Method method = WFRPTalentController.class
							.getDeclaredMethod(
									"getByName", new Class[] { String.class });
					Field field = WFRPTalentEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getTalents().get(i)) != null) {
							list = (List<Resource<WFRPTalentEntity>>) method
									.invoke(
											WFRPTalentController.getInstance(),
											(String) field.get(entity
													.getTalents().get(i)));
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
							if (field.get(entity.getTalents().get(i)) != null) {
								list = (List<Resource<WFRPTalentEntity>>) method
										.invoke(
												WFRPTalentController
														.getInstance(),
												(String) field
														.get(entity.getTalents()
																.get(i)));
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
					talents = list.get(0).getContent();
				}
				if (talents == null) {
					talents = (WFRPTalentEntity) ((Resource) WFRPTalentController
							.getInstance()
							.save(entity.getTalents().get(i)).get(0))
									.getContent();
				}
				entity.getTalents().set(i, talents);
				list = null;
			}
		}

		WFRPTalentChoiceEntity savedEntity = repository.save(entity);
		List<Resource<WFRPTalentChoiceEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
