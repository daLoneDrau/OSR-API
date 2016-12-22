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

import com.osrapi.models.wfrp.WFRPSkillChoiceEntity;
import com.osrapi.models.wfrp.WFRPSkillEntity;
import com.osrapi.repositories.wfrp.WFRPSkillChoiceRepository;

/**
 * @author drau
 */
@RestController
@RequestMapping(path = "/wfrp/skill_choices")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WFRPSkillChoiceController {
	/** the static instance of {@link WFRPSkillChoiceController}. */
	private static WFRPSkillChoiceController instance;
	/**
	 * Gets the static instance.
	 * @return {@link WFRPSkillChoiceController}
	 */
	public static WFRPSkillChoiceController getInstance() {
		if (instance == null) {
			new WFRPSkillChoiceController();
		}
		return instance;
	}
	/** the data repository. */
	@Autowired
	private WFRPSkillChoiceRepository repository;
	/** Creates a new instance of {@link WFRPSkillChoiceController}. */
	public WFRPSkillChoiceController() {
		instance = this;
	}
	/**
	 * Gets a list of {@link WFRPSkillChoiceEntity}s.
	 * @return {@link List}<{@link Resource}<{@link WFRPSkillChoiceEntity}>>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<WFRPSkillChoiceEntity>> getAll() {
		Iterator<WFRPSkillChoiceEntity> iter = repository.findAll()
				.iterator();
		List<Resource<WFRPSkillChoiceEntity>> resources = new ArrayList<Resource<WFRPSkillChoiceEntity>>();
		while (iter.hasNext()) {
			resources.add(getSkillChoiceResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a single {@link WFRPSkillChoiceEntity}.
	 * @param id the event type's id
	 * @return {@link List}<{@link Resource}<{@link WFRPSkillChoiceEntity}>>
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public List<Resource<WFRPSkillChoiceEntity>> getById(
			@PathVariable final Long id) {
		WFRPSkillChoiceEntity entity = repository.findOne(id);
		List<Resource<WFRPSkillChoiceEntity>> resources = new ArrayList<Resource<WFRPSkillChoiceEntity>>();
		resources.add(getSkillChoiceResource(entity));
		entity = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPSkillChoiceEntity}s that share a name.
	 * @param name the skill_choice' name
	 * @return {@link List}<{@link Resource}<{@link WFRPSkillChoiceEntity}>>
	 */
	@RequestMapping(path = "name/{name}", method = RequestMethod.GET)
	public List<Resource<WFRPSkillChoiceEntity>> getByName(
			@PathVariable final String name) {
		Iterator<WFRPSkillChoiceEntity> iter = repository.findByName(name)
				.iterator();
		List<Resource<WFRPSkillChoiceEntity>> resources = new ArrayList<Resource<WFRPSkillChoiceEntity>>();
		while (iter.hasNext()) {
			resources.add(getSkillChoiceResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a list of {@link WFRPSkillChoiceEntity}s that share a numToChoose.
	 * @param numToChoose the skill_choice' numToChoose
	 * @return {@link List}<{@link Resource}<{@link WFRPSkillChoiceEntity}>>
	 */
	@RequestMapping(path = "num_to_choose/{numToChoose}", method = RequestMethod.GET)
	public List<Resource<WFRPSkillChoiceEntity>> getByNumToChoose(
			@PathVariable final Long numToChoose) {
		Iterator<WFRPSkillChoiceEntity> iter = repository
				.findByNumToChoose(numToChoose)
				.iterator();
		List<Resource<WFRPSkillChoiceEntity>> resources = new ArrayList<Resource<WFRPSkillChoiceEntity>>();
		while (iter.hasNext()) {
			resources.add(getSkillChoiceResource(iter.next()));
		}
		iter = null;
		return resources;
	}
	/**
	 * Gets a {@link Resource} instance with links for the
	 * {@link WFRPSkillChoiceEntity}.
	 * @param entity the {@link WFRPSkillChoiceEntity}
	 * @return {@link Resource}<{@link WFRPSkillChoiceEntity}>
	 */
	private Resource<WFRPSkillChoiceEntity> getSkillChoiceResource(
			final WFRPSkillChoiceEntity entity) {
		Resource<WFRPSkillChoiceEntity> resource = new Resource<WFRPSkillChoiceEntity>(
				entity);
		// link to entity
		resource.add(ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(getClass()).getById(
						entity.getId()))
				.withSelfRel());
		return resource;
	}

	/**
	 * Saves multiple {@link WFRPSkillChoiceEntity}s.
	 * @param entities the list of {@link WFRPSkillChoiceEntity} instances
	 * @return {@link List}<{@link Resource}<{@link WFRPSkillChoiceEntity}>>
	 */
	@RequestMapping(path = "/bulk", method = RequestMethod.POST)
	public List<Resource<WFRPSkillChoiceEntity>> save(
			@RequestBody final List<WFRPSkillChoiceEntity> entities) {
		List<Resource<WFRPSkillChoiceEntity>> resources = new ArrayList<Resource<WFRPSkillChoiceEntity>>();
		Iterator<WFRPSkillChoiceEntity> iter = entities.iterator();
		while (iter.hasNext()) {
			resources.add(save(iter.next()).get(0));
		}
		iter = null;
		return resources;
	}
	/**
	 * Saves a single {@link WFRPSkillChoiceEntity}.
	 * @param entity the {@link WFRPSkillChoiceEntity} instance
	 * @return {@link List}<{@link Resource}<{@link WFRPSkillChoiceEntity}>>
	 */
	@RequestMapping(method = RequestMethod.POST)
	public List<Resource<WFRPSkillChoiceEntity>> save(
			@RequestBody final WFRPSkillChoiceEntity entity) {
		if (entity.getSkills() != null
				&& !entity.getSkills().isEmpty()) {
			for (int i = entity.getSkills().size() - 1; i >= 0; i--) {
				WFRPSkillEntity skills = null;
				List<Resource<WFRPSkillEntity>> list = null;
				try {
					Method method = WFRPSkillController.class.getDeclaredMethod(
							"getByName", new Class[] { String.class });
					Field field = WFRPSkillEntity.class
							.getDeclaredField("name");
					if (method != null
							&& field != null) {
						field.setAccessible(true);
						if (field.get(entity.getSkills().get(i)) != null) {
							list = (List<Resource<WFRPSkillEntity>>) method
									.invoke(
											WFRPSkillController.getInstance(),
											(String) field.get(
													entity.getSkills().get(i)));
						}
					}
					if (list == null) {
						method = WFRPSkillController.class.getDeclaredMethod(
								"getByCode", new Class[] { String.class });
						field = WFRPSkillEntity.class.getDeclaredField(
								"code");
						if (method != null
								&& field != null) {
							field.setAccessible(true);
							if (field.get(entity.getSkills().get(i)) != null) {
								list = (List<Resource<WFRPSkillEntity>>) method
										.invoke(
												WFRPSkillController
														.getInstance(),
												(String) field
														.get(entity.getSkills()
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
					skills = list.get(0).getContent();
				}
				if (skills == null) {
					skills = (WFRPSkillEntity) ((Resource) WFRPSkillController
							.getInstance()
							.save(entity.getSkills().get(i)).get(0))
									.getContent();
				}
				entity.getSkills().set(i, skills);
				list = null;
			}
		}

		WFRPSkillChoiceEntity savedEntity = repository.save(entity);
		List<Resource<WFRPSkillChoiceEntity>> list = getById(
				savedEntity.getId());
		savedEntity = null;
		return list;
	}
}
