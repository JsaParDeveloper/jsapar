---
layout: default
title: Upgrading from earlier versions of JSaPar
---
<h1>Upgrading from earlier versions of JSaPar</h1>
* TOC
{:toc}

## Upgrading from version 2.0
The following classes has moved to package `org.jsapar.bean` in order to make them visible externally when introducing modules: 
  * org.jsapar.parse.bean.BeanMap
  * org.jsapar.parse.bean.Xml2BeanMapBuilder

The following classes has moved to package `org.jsapar.text` in order to make them visible externally when introducing modules: 
  * org.jsapar.parse.text.TextParseConfig

## Upgrading from version 1.x
The 2.0 version of the library differs fundamentally from the 1.x versions so there is no easy way to upgrade. This
article will however try to help as much as possible.

Almost all package names has changed in 2.0.
## Converter
* The class `org.jsapar.io.Converter` is more or less replaced by `org.jsapar.Text2TextConverter`
* It is no longer possible to get a list of errors as return value from the converter, instead use `org.jsapar.error.RecordingErrorEventListener` if you want to handle all errors when done parsing.

## Outputter
* The class `org.jsapar.output.Outputter` is more or less replaced by `org.jsapar.TextComposer`

## Line
* All the model classes have moved from `org.jsapar` package to `org.jsapar.model`
* The utility methods to get and set cell values of different types on the `Line` class have moved to the `LineUtils` class and are now static methods.