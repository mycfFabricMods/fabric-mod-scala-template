package com.username.modid.blocks

import com.username.modid.MainFile
import net.minecraft.block.{AbstractBlock, Block, Blocks}
import net.minecraft.item.{BlockItem, Item, ItemGroup}
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

import scala.collection.mutable

object ModIdBlocks {

  private val BlockItemsRegistry = mutable.LinkedHashMap[String, Item]() //linkedMapOf<String, Item>()
  private val BlockRegistry = mutable.LinkedHashMap[String, Block]()

  /**
   * Register blocks here.
   * [net.minecraft.item.BlockItem]'s gets added automatically (but can't be referenced atm).
   * If you wish to change the settings of the BlockItem implement your own methods for it.
   */
  val COOL_BLOCK: Block = addBlock("coolblock", new Block(AbstractBlock.Settings.copy(Blocks.STONE)))


  private def addBlock(name: String, block: Block): Block = {
    val correctedName = name.replace(" ", "").toLowerCase.trim
    BlockRegistry.put(correctedName, block)
    BlockItemsRegistry.put(correctedName + "_item", new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.MISC)))
    block
  }

  def registerBlocks(): Unit = {
    this.BlockRegistry.foreach { it =>
      Registry.register(Registry.BLOCK, new Identifier(MainFile.MOD_ID, it._1), it._2)
    }

    def registerBlockItems(): Unit = {
      this.BlockItemsRegistry.foreach { it =>
        Registry.register(Registry.ITEM, new Identifier(MainFile.MOD_ID, it._1), it._2)
      }
    }

    registerBlockItems()
  }
}
