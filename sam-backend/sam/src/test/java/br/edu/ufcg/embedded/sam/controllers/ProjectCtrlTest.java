package br.edu.ufcg.embedded.sam.controllers;

import br.edu.ufcg.embedded.sam.models.Objective;
import br.edu.ufcg.embedded.sam.models.Project;
import br.edu.ufcg.embedded.sam.models.Role;
import br.edu.ufcg.embedded.sam.services.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
public class ProjectCtrlTest {


    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectCtrl projectCtrl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(projectCtrl)
                .build();
    }
    @Test
    public void test_get_all_projects() throws Exception {
        List<Project> projects = Arrays.asList(
                new Project("name","function", 5,  10, new HashMap<Role,Integer>(), null,  "projectType", new ArrayList<Objective>()),
                new Project());

        when(projectService.findAll()).thenReturn(projects);

        mockMvc.perform(get("/project/list"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].experienceTime", is(5)));

        verify(projectService, times(1)).findAll();
        verifyNoMoreInteractions(projectService);
    }
    @Test
    public void test_get_projects_byId() throws Exception {
        List<Project> projects = Arrays.asList(
                new Project("name","function", 5,  10, new HashMap<Role,Integer>(), null,  "projectType", new ArrayList<Objective>()),
                new Project());

        when(projectService.find(1)).thenReturn(projects.get(0));
        when(projectService.find(2)).thenReturn(projects.get(1));

        mockMvc.perform(get("/project/get-project/1"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name",is("name")));

        verify(projectService, times(1)).find(1);
        verifyNoMoreInteractions(projectService);
    }
    @Test
     public void test_project_create() throws Exception {
        Project project = new Project("Sam","function", 5,  10, new HashMap<Role,Integer>(), null,  "projectType", new ArrayList<Objective>());

        when(projectService.createOrUpdate(project)).thenReturn(project);
        mockMvc.perform(
                post("/project/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(project)))
                .andExpect(status().isCreated());
        verify(projectService, times(1)).createOrUpdate(any());
        verifyNoMoreInteractions(projectService);
    }

    @Test
    public void test_project_update() throws Exception {
        Project project = new Project("Sam","function", 5,  10, new HashMap<Role,Integer>(), null,  "projectType", new ArrayList<Objective>());
        when(projectService.createOrUpdate(project)).thenReturn(project);
        mockMvc.perform(
                put("/project/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(project)))
                .andExpect(status().isAccepted());
        verify(projectService, times(1)).createOrUpdate(any());
        verifyNoMoreInteractions(projectService);
    }

    @Test
    public void test_project_delete() throws Exception {
        Project project = new Project("Sam","function", 5,  10, new HashMap<Role,Integer>(), null,  "projectType", new ArrayList<Objective>());
        when(projectService.removeById(1)).thenReturn(true);
        mockMvc.perform(
                delete("/project/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(project)))
                .andExpect(status().isAccepted());
        verify(projectService, times(1)).removeById(1);
        verifyNoMoreInteractions(projectService);
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}
