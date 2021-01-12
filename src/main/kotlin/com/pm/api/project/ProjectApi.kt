package com.pm.api.project

import com.pm.api.NoRequestObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder


@RestController
@ResponseBody
@RequestMapping("/projects")
class ProjectApi @Autowired constructor(
    private val createProjectExecutor: CreateProjectExecutor,
    private val getAllProjectsExecutor: GetAllProjectsExecutor
) {

    @PostMapping("/")
    fun createProject(@RequestBody createProjectRequest: CreateProjectRequest): ResponseEntity<CreateProjectResponse> {
        val result = createProjectExecutor.execute(createProjectRequest)

        val uriComponents = ServletUriComponentsBuilder
            .fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(result.id)
            .toUri()

        return ResponseEntity.created(uriComponents)
            .body(result)
    }

    @GetMapping("/")
    fun getAllProjects(): GetAllProjectsResponse {
        return getAllProjectsExecutor.execute(NoRequestObject())
    }

    @GetMapping("/{id}")
    fun getProject(@PathVariable id: String) {
        // TODO
    }

    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: Long, @RequestBody updateProjectRequest: UpdateProjectRequest) {
        // TODO
    }

    @PatchMapping("/{id}")
    fun assignParticipant(@PathVariable id: Long, @RequestBody assignParticipantRequest: AssignParticipantRequest) {
        // TODO
    }

}
