package io.github.niterux.hccasfp.mixin;

import io.github.niterux.hccasfp.CapeItemOnHeadDuck;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.state.ArmorStandEntityRenderState;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandEntityRenderer.class)
public class ArmorStandEntityRendererMixin {
    @Unique
    private final Identifier CAPE_ITEM_MODEL = Items.PAPER.getComponents().getOrDefault(DataComponentTypes.ITEM_MODEL, Identifier.of("paper"));

    @Inject(method = "updateRenderState(Lnet/minecraft/entity/decoration/ArmorStandEntity;Lnet/minecraft/client/render/entity/state/ArmorStandEntityRenderState;F)V", at = @At("HEAD"))
    private void detectCape(ArmorStandEntity armorStand, ArmorStandEntityRenderState armorStandEntityRenderState, float f, CallbackInfo ci) {
        ((CapeItemOnHeadDuck) armorStandEntityRenderState).hccasfp$setIsCapeItemOnHead(false);

        final Entity vehicle = armorStand.getVehicle();
        if (vehicle == null)
            return;
        final ClientPlayerEntity minecraftPlayer = MinecraftClient.getInstance().player;
        if (minecraftPlayer != vehicle)
            return;

        final ItemStack headItemStack = armorStand.getEquippedStack(EquipmentSlot.HEAD);
        final ComponentMap headItemStackComponents = headItemStack.getComponents();
        final Identifier headItemModel = headItemStackComponents.get(DataComponentTypes.ITEM_MODEL);
        if (!CAPE_ITEM_MODEL.equals(headItemModel))
            return;

        final CustomModelDataComponent headItemStackCustomModel = headItemStackComponents.getOrDefault(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelDataComponent.DEFAULT);
        if (headItemStackCustomModel.floats().size() != 1 || !headItemStackCustomModel.strings().isEmpty())
            return;

        final Float customModelFirstFloatRef = headItemStackCustomModel.floats().getFirst();
        if (customModelFirstFloatRef < 100)
            return;

        ((CapeItemOnHeadDuck) armorStandEntityRenderState).hccasfp$setIsCapeItemOnHead(true);
    }
}
