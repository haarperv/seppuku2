package pw.seppuku.sprint;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pw.seppuku.event.bus.EventBus;
import pw.seppuku.feature.exception.FeatureException;
import pw.seppuku.feature.repository.FeatureRepository;
import pw.seppuku.metadata.Author;
import pw.seppuku.metadata.Version;
import pw.seppuku.plugin.AbstractPlugin;
import pw.seppuku.plugin.repository.PluginRepository;
import pw.seppuku.sprint.toggleable.features.SprintFeature;

public final class SeppukuSprintPlugin extends AbstractPlugin {

  private final static UUID SEPPUKU_SPRINT_UNIQUE_IDENTIFIER = UUID.fromString(
      "1a20d797-6e33-4e26-a7dd-c8e7cc33ca3e");
  private final static String SEPPUKU_SPRINT_HUMAN_IDENTIFIER = "seppuku-sprint";
  private final static Version SEPPUKU_SPRINT_VERSION = new Version(0, 1, 0, Optional.empty(),
      Optional.empty());
  private static final List<Author> SEPPUKU_SPRINT_AUTHORS = List.of(
      new Author("wine", Optional.of("Ossian"), Optional.of("Winter"),
          Optional.of("ossian@hey.com")));

  public SeppukuSprintPlugin() {
    super(SEPPUKU_SPRINT_UNIQUE_IDENTIFIER, SEPPUKU_SPRINT_HUMAN_IDENTIFIER, SEPPUKU_SPRINT_VERSION,
        SEPPUKU_SPRINT_AUTHORS);
  }

  @Override
  public void load(final EventBus eventBus, final FeatureRepository featureRepository,
      final PluginRepository pluginRepository) throws FeatureException {
    final var sprint = new SprintFeature(eventBus);
    featureRepository.add(sprint);
  }

  @Override
  public void unload(final EventBus eventBus, final FeatureRepository featureRepository,
      final PluginRepository pluginRepository) throws FeatureException {
    final var sprint = featureRepository.findFeatureByClass(SprintFeature.class);
    featureRepository.remove(sprint);
  }
}
