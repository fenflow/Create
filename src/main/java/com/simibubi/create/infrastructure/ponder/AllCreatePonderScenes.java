package com.simibubi.create.infrastructure.ponder;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.Create;
import com.simibubi.create.content.trains.track.TrackBlock;
import com.simibubi.create.content.trains.track.TrackMaterial;
import com.simibubi.create.infrastructure.ponder.scenes.KineticsScenes;
import com.simibubi.create.infrastructure.ponder.scenes.trains.TrackObserverScenes;
import com.simibubi.create.infrastructure.ponder.scenes.trains.TrackScenes;
import com.simibubi.create.infrastructure.ponder.scenes.trains.TrainScenes;
import com.simibubi.create.infrastructure.ponder.scenes.trains.TrainSignalScenes;
import com.simibubi.create.infrastructure.ponder.scenes.trains.TrainStationScenes;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class AllCreatePonderScenes {

	public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
		PonderSceneRegistrationHelper<ItemProviderEntry<?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);

		// WHITELIST-ONLY PONDER SCENES
		// Keeping only scenes for whitelisted items:
		// - create:shaft, create:cogwheel, create:large_cogwheel
		// - create:track, create:railway_casing (not in ponder, using cobblestone placeholder)
		// - create:water_wheel, create:hand_crank
		// - create:track_station, create:track_signal, create:track_observer
		// - create:controls (Train Controls)
		// - create:schedule
		// - create:schematicannon, create:schematic_table
		// - create:schematic_and_quill, create:empty_schematic, create:schematic

		// Kinetics - Whitelisted
		HELPER.forComponents(AllBlocks.SHAFT)
			.addStoryBoard("shaft/relay", KineticsScenes::shaftAsRelay, AllCreatePonderTags.KINETIC_RELAYS);

		HELPER.forComponents(AllBlocks.COGWHEEL)
			.addStoryBoard("cog/small", KineticsScenes::cogAsRelay, AllCreatePonderTags.KINETIC_RELAYS)
			.addStoryBoard("cog/speedup", KineticsScenes::cogsSpeedUp);

		HELPER.forComponents(AllBlocks.LARGE_COGWHEEL)
			.addStoryBoard("cog/speedup", KineticsScenes::cogsSpeedUp)
			.addStoryBoard("cog/large", KineticsScenes::largeCogAsRelay, AllCreatePonderTags.KINETIC_RELAYS);

		HELPER.addStoryBoard(AllBlocks.WATER_WHEEL, "water_wheel", KineticsScenes::waterWheel,
			AllCreatePonderTags.KINETIC_SOURCES);

		HELPER.addStoryBoard(AllBlocks.HAND_CRANK, "hand_crank", KineticsScenes::handCrank, AllCreatePonderTags.KINETIC_SOURCES);

		// Trains - Whitelisted
		HELPER.forComponents(TrackMaterial.allBlocks()
				.stream()
				.map((trackSupplier) -> new BlockEntry<TrackBlock>(
					// note: these blocks probably WON'T be in the Create Registrate, but a simple
					// code trace reveals the Entry's registrate isn't used
					Create.registrate(),
					RegistryObject.create(ForgeRegistries.BLOCKS.getKey(trackSupplier.get()), ForgeRegistries.BLOCKS)
				))
				.toArray(BlockEntry[]::new))
			.addStoryBoard("train_track/placement", TrackScenes::placement)
			.addStoryBoard("train_track/portal", TrackScenes::portal)
			.addStoryBoard("train_track/chunks", TrackScenes::chunks);

		HELPER.forComponents(AllBlocks.TRACK_STATION)
			.addStoryBoard("train_station/assembly", TrainStationScenes::assembly)
			.addStoryBoard("train_station/schedule", TrainStationScenes::autoSchedule);

		HELPER.forComponents(AllBlocks.TRACK_SIGNAL)
			.addStoryBoard("train_signal/placement", TrainSignalScenes::placement)
			.addStoryBoard("train_signal/signaling", TrainSignalScenes::signaling)
			.addStoryBoard("train_signal/redstone", TrainSignalScenes::redstone);

		HELPER.forComponents(AllItems.SCHEDULE)
			.addStoryBoard("train_schedule", TrainScenes::schedule);

		HELPER.forComponents(AllBlocks.TRAIN_CONTROLS)
			.addStoryBoard("train_controls", TrainScenes::controls);

		HELPER.forComponents(AllBlocks.TRACK_OBSERVER)
			.addStoryBoard("train_observer", TrackObserverScenes::observe);

	}
}
