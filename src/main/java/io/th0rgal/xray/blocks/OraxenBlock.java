package io.th0rgal.xray.blocks;

import io.th0rgal.oraxen.items.ItemTemplate;
import io.th0rgal.oraxen.mechanics.provided.gameplay.noteblock.NoteBlockMechanic;
import io.th0rgal.oraxen.mechanics.provided.gameplay.noteblock.NoteBlockMechanicFactory;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.inventory.ItemStack;

public class OraxenBlock extends BlockWrapper {

    private final ItemStack item;
    private final int customVariation;

    public OraxenBlock(String name) {
        super("oraxen", name);
        item = ItemTemplate.getItemTemplates().get(name).buildItem().build();
        customVariation = (NoteBlockMechanicFactory.getInstance().getMechanic(name))
                .getCustomVariation();
    }

    @Override
    public boolean isSimilarTo(Block block) {
        if (block.getType() != Material.NOTE_BLOCK)
            return false;
        final NoteBlock noteBlok = (NoteBlock) block.getBlockData();
        return ((int) (noteBlok.getInstrument().getType()) * 25
                + (int) noteBlok.getNote().getId() + (noteBlok.isPowered() ? 400 : 0) - 26) == customVariation;
    }

    @Override
    public ItemStack getAsItem() {
        return item;
    }

}
