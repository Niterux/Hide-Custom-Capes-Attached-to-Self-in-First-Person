package io.github.niterux.hccasfp.mixin;

import io.github.niterux.hccasfp.CapeItemOnHeadDuck;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin implements CapeItemOnHeadDuck {
    @Unique
    public boolean hccasfp$isCapeItemOnHead = false;

    @Override
    public boolean hccasfp$getIsCapeItemOnHead() {
        return hccasfp$isCapeItemOnHead;
    }

    @Override
    public void hccasfp$setIsCapeItemOnHead(boolean capeItem) {
        hccasfp$isCapeItemOnHead = capeItem;
    }

}
