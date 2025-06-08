package io.github.niterux.hccasfp.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.niterux.hccasfp.CapeItemOnHeadDuck;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<S extends LivingEntityRenderState> {
    @ModifyExpressionValue(method = "render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;shouldRenderFeatures(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;)Z"))
    private boolean overrideItemRenderingConditional(boolean original, S livingEntityRenderState) {
        final MinecraftClient minecraftClient = MinecraftClient.getInstance();
        if (((CapeItemOnHeadDuck) livingEntityRenderState).hccasfp$getIsCapeItemOnHead() && minecraftClient.options.getPerspective().isFirstPerson())
            return false;

        return original;
    }
}
