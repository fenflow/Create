package com.simibubi.create.infrastructure.ponder;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.Create;
import com.tterrag.registrate.util.entry.RegistryEntry;

import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class AllCreatePonderTags {

	public static final ResourceLocation

	KINETIC_RELAYS = loc("kinetic_relays"),
	KINETIC_SOURCES = loc("kinetic_sources"),
	TRAIN_RELATED = loc("train_related"),
	DISPLAY_SOURCES = loc("display_sources"),
	DISPLAY_TARGETS = loc("display_targets"),
	THRESHOLD_SWITCH_TARGETS = loc("threshold_switch_targets");

	private static ResourceLocation loc(String id) {
		return Create.asResource(id);
	}

	    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {

		PonderTagRegistrationHelper<RegistryEntry<?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);

		helper.registerTag(KINETIC_RELAYS)
			.addToIndex()
			.item(AllBlocks.COGWHEEL.get(), true, false)
			.title("Kinetic Blocks")
			.description("Components which help relaying Rotational Force elsewhere")
			.register();

		helper.registerTag(KINETIC_SOURCES)
			.addToIndex()
			.item(AllBlocks.WATER_WHEEL.get(), true, false)
			.title("Kinetic Sources")
			.description("Components which generate Rotational Force")
			.register();

		helper.registerTag(TRAIN_RELATED)
			.addToIndex()
			.item(AllBlocks.TRACK.get(), true, false)
			.title("Railway Equipment")
			.description("Components used in the construction or management of Train Contraptions")
			.register();

		// Minimal registrations for UI references; keep empty to respect whitelist
		helper.registerTag(DISPLAY_SOURCES)
			.item(AllBlocks.SHAFT.get(), true, false)
			.title("Display Link Sources")
			.description("Source components for display links")
			.register();

		helper.registerTag(DISPLAY_TARGETS)
			.item(AllBlocks.SHAFT.get(), true, false)
			.title("Display Link Targets")
			.description("Target components for display links")
			.register();

		helper.registerTag(THRESHOLD_SWITCH_TARGETS)
			.item(AllBlocks.SHAFT.get(), true, false)
			.title("Threshold Switch Targets")
			.description("Target components for threshold switches")
			.register();

		HELPER.addToTag(KINETIC_RELAYS)
			.add(AllBlocks.SHAFT)
			.add(AllBlocks.COGWHEEL)
			.add(AllBlocks.LARGE_COGWHEEL);

		HELPER.addToTag(KINETIC_SOURCES)
			.add(AllBlocks.WATER_WHEEL)
			.add(AllBlocks.HAND_CRANK);

		HELPER.addToTag(TRAIN_RELATED)
			.add(AllBlocks.TRACK)
			.add(AllBlocks.TRACK_STATION)
			.add(AllBlocks.TRACK_SIGNAL)
			.add(AllBlocks.TRACK_OBSERVER)
			.add(AllBlocks.TRAIN_CONTROLS)
			.add(AllItems.SCHEDULE)
			.add(AllBlocks.RAILWAY_CASING);
	    }

}
