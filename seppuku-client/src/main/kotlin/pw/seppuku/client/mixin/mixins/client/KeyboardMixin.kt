package pw.seppuku.client.mixin.mixins.client

import net.minecraft.client.Keyboard
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import pw.seppuku.client.Seppuku
import pw.seppuku.client.components.client.KeyboardOnKey
import pw.seppuku.client.mixin.ActualThis
import pw.seppuku.components.Toggle
import pw.seppuku.feature.filterComponent
import pw.seppuku.feature.mapComponent

@Mixin(Keyboard::class)
abstract class KeyboardMixin : ActualThis<Keyboard> {

    @Inject(method = ["onKey"], at = [At("HEAD")])
    private fun onKey(window: Long, key: Int, scanCode: Int, action: Int, modifiers: Int, callback: CallbackInfo) =
        Seppuku.featureRepository.findAll()
            .filterComponent(false, Toggle.ENABLED)
            .mapComponent<KeyboardOnKey>()
            .forEach { it.onKeyboardOnKey(actualThis, window, key, scanCode, action, modifiers) }
}