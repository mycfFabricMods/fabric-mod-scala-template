package com.username.modid

import com.username.modid.blocks.ModIdBlocks
import com.username.modid.items.ModIdItems
import net.fabricmc.api.ModInitializer

/**
 * Main File
 * VM options for mixins:
 * -Dmixin.debug.export=true (exports mixins into run/mixin.out/)
 * -Dmixin.debug=true (turns on all debugging features)
 */
object MainFile extends ModInitializer {
  given MOD_ID: String = "modid"

  override def onInitialize(): Unit = {
    ModIdBlocks.registerBlocks()
    ModIdItems.registerItems()
  }
}
