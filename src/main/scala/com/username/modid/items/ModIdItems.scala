package com.username.modid.items

import com.username.modid.MainFile
import net.minecraft.item.{Item, ItemGroup}
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModIdItems {
  private val ItemRegistry = collection.mutable.LinkedHashMap[String, Item]()


  /**
   * Register [net.minecraft.item.Item]'s in here.
   */
  val COOL_ITEM: Item = addItem("coolitem", new Item(new Item.Settings().maxCount(64).group(ItemGroup.MISC)))

  private def addItem[I <: Item](name: String, item: I): I = {
    val correctedName = name.replace(" ", "").toLowerCase.trim
    ItemRegistry.put(correctedName, item)
    item
  }

  def registerItems(): Unit = {
    this.ItemRegistry.foreach { it =>
      Registry.register(Registry.ITEM, new Identifier(MainFile.MOD_ID, it._1), it._2)
    }
  }
}
