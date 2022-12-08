package pw.seppuku.client.mixin.mixins.client.network

import net.minecraft.client.network.ClientPlayerEntity
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import pw.seppuku.client.Seppuku
import pw.seppuku.client.components.client.network.ClientPlayerEntityTick
import pw.seppuku.client.mixin.ActualThis
import pw.seppuku.components.Toggle
import pw.seppuku.feature.filterComponent
import pw.seppuku.feature.mapComponent

@Mixin(ClientPlayerEntity::class)
abstract class ClientPlayerEntityMixin : ActualThis<ClientPlayerEntity> {

    @Inject(method = ["tick"], at = [At("HEAD")])
    private fun onTickHead(callback: CallbackInfo) =
        Seppuku.featureRepository.findAll()
            .filterComponent<Toggle>(false)
            .mapComponent<ClientPlayerEntityTick>()
            .forEach { it.onClientPlayerEntityTick(actualThis) }
}