package com.dss.actor.service;

import com.dss.actor.exception.AbstractRuntimeException;
import com.dss.actor.model.Actor;
import com.dss.actor.model.Movie;
import com.dss.actor.proxy.MovieProxy;
import com.dss.actor.repository.ActorDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class ActorServiceTest {

    @MockBean ActorDao actorDao;
    @MockBean MovieProxy movieProxy;

    @Autowired ActorService actorService;

    @Test
    void Saving_an_actor_with_null_request_is_null() {

        Assertions.assertNull(actorService.save(null));
    }

    @Test
    void Saving_an_actor_with_valid_request_is_valid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Actor actor = new Actor();
        actor.setId(testId);

        Assertions.assertNotNull(actor);
        Assertions.assertNull(actor.getMovieList());
        Mockito.when(actorDao.save(actor)).thenReturn(actor);
        Assertions.assertEquals(actorService.save(actor), testId);
    }

    @Test
    void Saving_an_actor_with_valid_request_and_movielist_is_valid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Movie movie = new Movie();
        movie.setId("4028c4ec84419c76018441a921e80001");
        movie.setDescription("Inception");
        movie.setProductionCost("160M USD");
        movie.setYearReleased("2010");
        movie.setImageDirectory("/directory/do_not_delete.jpg");

        Actor actor = new Actor();
        actor.setId(testId);
        actor.setMovieList(Set.of(movie));

        Assertions.assertNotNull(actor);
        Assertions.assertNotNull(actor.getMovieList());

        Mockito.when(movieProxy.findAll()).thenReturn(Arrays.asList(movie));

        Mockito.when(actorDao.save(actor)).thenReturn(actor);
        Assertions.assertEquals(actorService.save(actor), testId);
    }

    @Test
    void Saving_an_actor_with_valid_request_and_nonexistent_movie_is_invalid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Movie movie = new Movie();
        movie.setId("4028c4ec84419c76018441a921e80001");
        movie.setDescription("Inception");
        movie.setProductionCost("160M USD");
        movie.setYearReleased("2010");
        movie.setImageDirectory("/directory/do_not_delete.jpg");

        Actor actor = new Actor();
        actor.setId(testId);
        actor.setMovieList(Set.of(movie));

        Assertions.assertNotNull(actor);
        Assertions.assertNotNull(actor.getMovieList());

        Mockito.when(movieProxy.findAll()).thenReturn(Arrays.asList(new Movie()));
        Assertions.assertThrows(AbstractRuntimeException.class, () -> {
            actorService.save(actor);
        });
    }

    @Test
    void Finding_an_actor_with_valid_id_is_valid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Mockito.when(actorDao.findById(testId)).thenReturn(Optional.of(new Actor()));
        Assertions.assertNotNull(actorService.findById(testId));
    }

    @Test
    void Finding_an_actor_with_invalid_id_is_invalid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Mockito.when(actorDao.findById(testId)).thenReturn(Optional.empty());
        Assertions.assertNull(actorService.findById(testId));
    }

    @Test
    void Find_all_existing_actors() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Movie movie = new Movie();
        movie.setId("4028c4ec84419c76018441a921e80001");
        movie.setDescription("Inception");
        movie.setProductionCost("160M USD");
        movie.setYearReleased("2010");
        movie.setImageDirectory("/directory/do_not_delete.jpg");

        Actor actor = new Actor();
        actor.setId(testId);
        actor.setAge("47");
        actor.setFirstName("Leonardo");
        actor.setLastName("DiCaprio");
        actor.setGender("M");
        actor.setMovieList(Set.of(movie));

        Mockito.when(actorDao.findAll()).thenReturn(Arrays.asList(actor));
        Assertions.assertEquals(actorService.findAll(), Arrays.asList(actor));
    }

    @Test
    void Updating_a_nonexistent_actor_is_error() {
        String testId = "testId";

        Mockito.when(actorDao.findById(testId)).thenReturn(Optional.empty());
        Assertions.assertThrows(AbstractRuntimeException.class, () -> {
            actorService.update(testId, new Actor());
        });
    }

    @Test
    void Updating_an_existing_actor_with_valid_request_is_valid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Actor actor = new Actor();
        actor.setId(testId);
        actor.setAge("47");
        actor.setFirstName("Leonardo");
        actor.setLastName("DiCaprio");
        actor.setGender("M");

        Mockito.when(actorDao.findById(testId)).thenReturn(Optional.of(actor));
        Assertions.assertTrue(actorService.update(testId, new Actor()));
    }

    @Test
    void Updating_an_existing_actor_with_null_request_will_do_nothing() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Actor actor = new Actor();
        actor.setId(testId);
        actor.setAge(null);
        actor.setFirstName(null);
        actor.setLastName(null);
        actor.setGender(null);

        Assertions.assertNull(actor.getMovieList());
        Mockito.when(actorDao.findById(testId)).thenReturn(Optional.of(actor));
        Assertions.assertTrue(actorService.update(testId, actor));
    }

    @Test
    void Updating_an_existing_actor_with_valid_movielist_is_valid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Movie movie = new Movie();
        movie.setId("4028c4ec84419c76018441a921e80001");
        movie.setDescription("Inception");
        movie.setProductionCost("160M USD");
        movie.setYearReleased("2010");
        movie.setImageDirectory("/directory/do_not_delete.jpg");

        Actor actor = new Actor();
        actor.setId(testId);
        actor.setAge(null);
        actor.setFirstName(null);
        actor.setLastName(null);
        actor.setGender(null);
        actor.setMovieList(Set.of(movie));

        Assertions.assertNotNull(actor.getMovieList());
        Mockito.when(movieProxy.findAll()).thenReturn(Arrays.asList(movie));
        Mockito.when(actorDao.findById(testId)).thenReturn(Optional.of(actor));
        Assertions.assertTrue(actorService.update(testId, actor));
    }

    @Test
    void Deleting_an_actor_with_no_movies_is_valid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Actor actor = new Actor();
        actor.setId(testId);
        actor.setMovieList(null);

        Mockito.when(actorDao.findById(testId)).thenReturn(Optional.of(new Actor()));
        Assertions.assertNull(actor.getMovieList());
        actorDao.deleteById(testId);
    }

    @Test
    void Deleting_an_actor_with_movies_is_invalid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Movie movie = new Movie();
        movie.setId("4028c4ec84419c76018441a921e80001");
        movie.setDescription("Inception");
        movie.setProductionCost("160M USD");
        movie.setYearReleased("2010");
        movie.setImageDirectory("/directory/do_not_delete.jpg");

        Actor actor = new Actor();
        actor.setId(testId);
        actor.setMovieList(Set.of(movie));

        Mockito.when(actorDao.findById(testId)).thenReturn(Optional.of(actor));
        Assertions.assertNotNull(actor.getMovieList());
        Assertions.assertTrue(!actor.getMovieList().isEmpty());
        Assertions.assertThrows(AbstractRuntimeException.class, () -> {
            actorService.deleteById(testId);
        });
    }
}
