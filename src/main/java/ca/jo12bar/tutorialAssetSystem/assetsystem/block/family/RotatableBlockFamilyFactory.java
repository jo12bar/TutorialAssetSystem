/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.jo12bar.tutorialAssetSystem.assetsystem.block.family;

import java.util.Map;
import java.util.Set;

import org.terasology.math.Pitch;
import org.terasology.math.Roll;
import org.terasology.math.Rotation;
import org.terasology.math.Side;
import org.terasology.math.Yaw;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockBuilderHelper;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.BlockFamily;
import org.terasology.world.block.family.BlockFamilyFactory;
import org.terasology.world.block.family.RegisterBlockFamilyFactory;
import org.terasology.world.block.loader.BlockFamilyDefinition;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

@RegisterBlockFamilyFactory("all")
public class RotatableBlockFamilyFactory implements BlockFamilyFactory {

    public static final ImmutableSet<String> BLOCK_NAMES = ImmutableSet.of("front", "left", "right", "back", "top", "bottom");

    /**
     * Defines rotations for all possible rotations of one block.
     *
     * The first key is the side that should be on top, and the second key is the side for the front. So, for TOP, FRONT
     * we need no rotation. For BOTTOM, BACK we need a 180 degree yaw and a 180 degree roll.
     */
    public static final Map<Side, Map<Side, Rotation>> BLOCK_ROTATIONS = blockRotations();

    private static Map<Side, Map<Side, Rotation>> blockRotations() {
        Map<Side, Map<Side, Rotation>> rotations = Maps.newEnumMap(Side.class);

        // Rotations where the TOP is on top and we rotate the front (add yaw)
        Map<Side, Rotation> topOnTopRotations = Maps.newEnumMap(Side.class);
        topOnTopRotations.put(Side.FRONT, Rotation.rotate(Yaw.NONE, Pitch.NONE, Roll.NONE));
        topOnTopRotations.put(Side.RIGHT, Rotation.rotate(Yaw.CLOCKWISE_90, Pitch.NONE, Roll.NONE));
        topOnTopRotations.put(Side.BACK, Rotation.rotate(Yaw.CLOCKWISE_180, Pitch.NONE, Roll.NONE));
        topOnTopRotations.put(Side.LEFT, Rotation.rotate(Yaw.CLOCKWISE_270, Pitch.NONE, Roll.NONE));
        rotations.put(Side.TOP, topOnTopRotations);

        //rotations where FRONT is on top (pitch 90) and we rotate the front (add yaw)
        Map<Side, Rotation> frontOnTopRotations = Maps.newEnumMap(Side.class);
        frontOnTopRotations.put(Side.BOTTOM, Rotation.rotate(Yaw.NONE, Pitch.CLOCKWISE_90, Roll.NONE));
        frontOnTopRotations.put(Side.BACK, Rotation.rotate(Yaw.CLOCKWISE_90, Pitch.CLOCKWISE_90, Roll.NONE));
        frontOnTopRotations.put(Side.TOP, Rotation.rotate(Yaw.CLOCKWISE_180, Pitch.CLOCKWISE_90, Roll.NONE));
        frontOnTopRotations.put(Side.FRONT, Rotation.rotate(Yaw.CLOCKWISE_270, Pitch.CLOCKWISE_90, Roll.NONE));
        rotations.put(Side.FRONT, frontOnTopRotations);

        //rotations where RIGHT is on top (roll 270) and we rotate the front (add yaw)
        Map<Side, Rotation> rightOnTopRotations = Maps.newEnumMap(Side.class);
        rightOnTopRotations.put(Side.FRONT, Rotation.rotate(Yaw.NONE, Pitch.NONE, Roll.CLOCKWISE_270));
        rightOnTopRotations.put(Side.BOTTOM, Rotation.rotate(Yaw.CLOCKWISE_90, Pitch.NONE, Roll.CLOCKWISE_270));
        rightOnTopRotations.put(Side.BACK, Rotation.rotate(Yaw.CLOCKWISE_180, Pitch.NONE, Roll.CLOCKWISE_270));
        rightOnTopRotations.put(Side.TOP, Rotation.rotate(Yaw.CLOCKWISE_270, Pitch.NONE, Roll.CLOCKWISE_270));
        rotations.put(Side.RIGHT, rightOnTopRotations);

        //rotations where LEFT is on top (roll 90) and we rotate the front (add yaw)
        Map<Side, Rotation> leftOnTopRotations = Maps.newEnumMap(Side.class);
        leftOnTopRotations.put(Side.FRONT, Rotation.rotate(Yaw.NONE, Pitch.NONE, Roll.CLOCKWISE_90));
        leftOnTopRotations.put(Side.TOP, Rotation.rotate(Yaw.CLOCKWISE_90, Pitch.NONE, Roll.CLOCKWISE_90));
        leftOnTopRotations.put(Side.BACK, Rotation.rotate(Yaw.CLOCKWISE_180, Pitch.NONE, Roll.CLOCKWISE_90));
        leftOnTopRotations.put(Side.BOTTOM, Rotation.rotate(Yaw.CLOCKWISE_270, Pitch.NONE, Roll.CLOCKWISE_90));
        rotations.put(Side.LEFT, leftOnTopRotations);

        //rotations where BACK is on top (pitch 270) and we rotate the front (add yaw)
        Map<Side, Rotation> backOnTopRotations = Maps.newEnumMap(Side.class);
        backOnTopRotations.put(Side.TOP, Rotation.rotate(Yaw.NONE, Pitch.CLOCKWISE_270, Roll.NONE));
        backOnTopRotations.put(Side.RIGHT, Rotation.rotate(Yaw.CLOCKWISE_90, Pitch.CLOCKWISE_270, Roll.NONE));
        backOnTopRotations.put(Side.BOTTOM, Rotation.rotate(Yaw.CLOCKWISE_180, Pitch.CLOCKWISE_270, Roll.NONE));
        backOnTopRotations.put(Side.LEFT, Rotation.rotate(Yaw.CLOCKWISE_270, Pitch.CLOCKWISE_270, Roll.NONE));
        rotations.put(Side.BACK, backOnTopRotations);

        //rotations where BOTTOM is on top (pitch 180) and we rotate the front (add yaw)
        Map<Side, Rotation> bottomOnTopRotations = Maps.newEnumMap(Side.class);
        bottomOnTopRotations.put(Side.FRONT, Rotation.rotate(Yaw.NONE, Pitch.CLOCKWISE_180, Roll.NONE));
        bottomOnTopRotations.put(Side.LEFT, Rotation.rotate(Yaw.CLOCKWISE_90, Pitch.CLOCKWISE_180, Roll.NONE));
        bottomOnTopRotations.put(Side.BACK, Rotation.rotate(Yaw.CLOCKWISE_180, Pitch.CLOCKWISE_180, Roll.NONE));
        bottomOnTopRotations.put(Side.RIGHT, Rotation.rotate(Yaw.CLOCKWISE_270, Pitch.CLOCKWISE_180, Roll.NONE));
        rotations.put(Side.BOTTOM, bottomOnTopRotations);

        return rotations;
    }

    @Override
    public BlockFamily createBlockFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        Map<Side, Map<Side, Block>> blockMap = Maps.newEnumMap(Side.class);

        // The default definition will be the top side and the front facing side.
        for (Side topSide : BLOCK_ROTATIONS.keySet()) {
            Map<Side, Rotation> frontSides = BLOCK_ROTATIONS.get(topSide);
            Map<Side, Block> frontBlocks = Maps.newEnumMap(Side.class);

            for (Side frontSide : frontSides.keySet()) {
                Rotation rotation = frontSides.get(frontSide);
                frontBlocks.put(frontSide, blockBuilder.constructTransformedBlock(definition, rotation));
            }

            blockMap.put(topSide, frontBlocks);
        }

        return new RotatableBlockFamily(new BlockUri(definition.getUrn()), blockMap, definition.getCategories());
    }

    @Override
    public Set<String> getSectionNames() {
        return BLOCK_NAMES;
    }
}
