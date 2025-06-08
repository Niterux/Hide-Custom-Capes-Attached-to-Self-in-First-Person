package io.github.niterux.hccasfp.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.niterux.hccasfp.CapeItemOnHeadDuck;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> {
    @ModifyExpressionValue(method = "render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;shouldRenderFeatures(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;)Z"))
    private boolean overrideItemRendering(boolean original, S livingEntityRenderState) {
        final MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (((CapeItemOnHeadDuck) livingEntityRenderState).hccasfp$getCapeItemOnHead() && minecraftClient.options.getPerspective().isFirstPerson()) {
            return false;
        }
        return original;
    }
}
