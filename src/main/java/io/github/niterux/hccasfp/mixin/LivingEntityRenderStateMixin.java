package io.github.niterux.hccasfp.mixin;

import io.github.niterux.hccasfp.CapeItemOnHeadDuck;
import net.minecraft.client.render.entity.state.ArmorStandEntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin implements CapeItemOnHeadDuck {
    @Unique
    public boolean hccasfp$capeItemOnHead = false;
    @Override
    public boolean hccasfp$getCapeItemOnHead() {
        return hccasfp$capeItemOnHead;
    }
    @Override
    public void hccasfp$setCapeItemOnHead(boolean capeItem) {
        hccasfp$capeItemOnHead = capeItem;
    }

}
