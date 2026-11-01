package in.voxpixel.skills;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.item.ItemContext;
import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.registry.NamespacedRegistry;
import dev.aurelium.auraskills.api.skill.CustomSkill;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoxPixelSkills extends JavaPlugin {

    @Override
    public void onEnable() {

        AuraSkillsApi api = AuraSkillsApi.get();

        if (api == null) {
            getLogger().severe("AuraSkills API not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        NamespacedRegistry registry =
                api.useRegistry("voxskills", getDataFolder());

        register(registry, "taming", "Taming", "bone", "0,0");
        register(registry, "carpentry", "Carpentry", "crafting_table", "1,0");
        register(registry, "runecrafting", "Runecrafting", "enchanting_table", "2,0");
        register(registry, "social", "Social", "player_head", "3,0");
        register(registry, "dungeoneering", "Dungeoneering", "iron_sword", "4,0");

        getLogger().info("VOXPIXEL custom skills registered!");
    }

    private void register(
            NamespacedRegistry registry,
            String id,
            String name,
            String material,
            String position
    ) {
        CustomSkill skill = CustomSkill
                .builder(NamespacedId.of("voxskills", id))
                .displayName(name)
                .description(name + " Skill")
                .item(ItemContext.builder()
                        .material(material)
                        .pos(position)
                        .build())
                .build();

        registry.registerSkill(skill);
    }
}
