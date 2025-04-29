package com.coded.spring

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
	applicationConfig.getMapConfig("Menu").setTimeToLiveSeconds(120)
}

val applicationConfig = Config("application-cache")
val serverCache: HazelcastInstance = Hazelcast.newHazelcastInstance(applicationConfig)