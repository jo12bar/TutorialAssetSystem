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
package ca.jo12bar.tutorialAssetSystem.assetsystem.systems;

import ca.jo12bar.tutorialAssetSystem.assetsystem.components.RotateBlockOnActivateComponent;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.common.ActivateEvent;
import org.terasology.math.Side;
import org.terasology.registry.In;
import org.terasology.utilities.random.FastRandom;
import org.terasology.utilities.random.Random;
import org.terasology.world.WorldProvider;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockComponent;

import java.util.List;

@RegisterSystem(RegisterMode.AUTHORITY)
public class RotateBlockOnActivationSystem extends BaseComponentSystem {

    @In
    private WorldProvider worldProvider;

    private Random random = new FastRandom();

    public static final Logger LOG = LoggerFactory.getLogger(RotateBlockOnActivationSystem.class);

    @ReceiveEvent(components = {RotateBlockOnActivateComponent.class, BlockComponent.class})
    public void onActivate(ActivateEvent event, EntityRef entity) {
        BlockComponent blockComponent = entity.getComponent(BlockComponent.class);
        Block block = blockComponent.getBlock();
        List<Block> blocks = Lists.newArrayList(block.getBlockFamily().getBlocks());

        // Subtract 1 because we omit the block with the same rotation as the current one:
        int index = random.nextInt(blocks.size() - 1);

        Side currentDirection = block.getDirection();

        for (int i = 0; i < blocks.size(); i++) {
            Block newBlock = blocks.get(i);

            if (newBlock.getDirection() != currentDirection) {
                index--;
            }

            if (index == 0) {
                LOG.info(newBlock.getURI().toString());
                worldProvider.setBlock(blockComponent.getPosition(), newBlock);
                return;
            }
        }
    }

}
