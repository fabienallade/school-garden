package com.allade.afric.word.config

import com.allade.afric.word.model.User
import com.allade.afric.word.repository.RoleRepository
import com.allade.afric.word.repository.UserRepository
import org.springframework.boot.CommandLineRunner

class DataLoader(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        userRepository.deleteAll()
        roleRepository.deleteAll()
        val users =
            listOf<User>(
                User("allade", "fabien", "alladefabien@gmail.com", "", ""),
                User("jean", "ocoto", "jcoto@gmail.com", "", ""),
                User("paul", "adjame", "adjame@gmail.com", "", ""),
                User("canlanta", "ekoto", "ekoto@gmail.com", "", ""),
                User("soso", "evoli", "evoli@gmail.com", "", ""),
                User("ploto", "vlicoki", "vlicoki@gmail.com", "", ""),
                User("klakla", "jipolo", "jipolo@gmail.com", "", ""),
                User("indofo", "molipo", "molipo@gmail.com", "", ""),
            )
        for (user in users) {
            userRepository.save(user)
        }
    }
}
