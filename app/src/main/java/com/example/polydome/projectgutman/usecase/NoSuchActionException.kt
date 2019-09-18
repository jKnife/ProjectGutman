package com.example.polydome.projectgutman.usecase

import java.lang.Exception

class NoSuchActionException(actionId: Int): Exception("Action with id [$actionId] not found")