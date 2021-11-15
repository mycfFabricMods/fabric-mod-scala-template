package com.username.modid.language_adapter

import net.fabricmc.loader.api.{LanguageAdapter, ModContainer}

class CustomLanguageAdapter extends LanguageAdapter {
  override def create[T](mod: ModContainer, value: String, aClass: Class[T]): T =
    Class.forName(value + "$").getField("MODULE$").get(null).asInstanceOf[T]
}
