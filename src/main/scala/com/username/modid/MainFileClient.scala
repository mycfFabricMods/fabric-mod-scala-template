package com.username.modid

import net.fabricmc.api.{ClientModInitializer, EnvType, Environment}

@Environment(EnvType.CLIENT)
object MainFileClient extends ClientModInitializer {
  override def onInitializeClient(): Unit = {
  }
}
