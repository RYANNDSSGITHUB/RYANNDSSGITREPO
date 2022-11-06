package com.dss.movie.service;

import com.dss.movie.exception.AbstractRuntimeException;
import com.dss.movie.model.Actor;
import com.dss.movie.model.Movie;
import com.dss.movie.model.MovieRequestModel;
import com.dss.movie.model.Review;
import com.dss.movie.proxy.ActorProxy;
import com.dss.movie.repository.MovieDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
class MovieServiceTest {

    @MockBean MovieDao movieDao;
    @MockBean ActorProxy actorProxy;

    @Autowired MovieService movieService;

    @Test
    void Finding_a_movie_with_valid_id_is_valid() {
        String testId = "4028c4ec84419c76018441a921e80001";

        Mockito.when(movieDao.findById(testId)).thenReturn(Optional.of(new Movie()));
        Assertions.assertNotNull(movieService.findById(testId));
    }

    @Test
    void Finding_a_movie_with_invalid_id_is_invalid() {
        String testId = "4028c4ec84419c76018441a921e80001";

        Mockito.when(movieDao.findById(testId)).thenReturn(Optional.empty());
        Assertions.assertNull(movieService.findById(testId));
    }

    @Test
    void Saving_new_movie_with_valid_request_and_no_actors_is_valid() {
        String testId = "Id";

        Movie movie = new Movie();
        movie.setId("Id");
        movie.setDescription("Movie name");
        movie.setProductionCost("Production Cost");
        movie.setYearReleased("Year Released");
        movie.setImageDirectory("Image Directory");
        movie.setActorList(null);

        Mockito.when(movieDao.save(movie)).thenReturn(movie);
        Assertions.assertNull(movie.getActorList());
        Assertions.assertEquals(movieService.save(movie), testId);
    }

    @Test
    void Saving_new_movie_with_valid_request_and_invalid_actor_list_is_invalid() {
        String testId = "Id";

        Actor actor = new Actor();

        Movie movie = new Movie();
        movie.setId(testId);
        movie.setDescription("Inception");
        movie.setProductionCost("160M USD");
        movie.setYearReleased("2010");
        movie.setImageDirectory("/directory/do_not_delete.jpg");
        movie.setActorList(Set.of(actor));

        Mockito.when(movieDao.save(movie)).thenReturn(movie);
        Assertions.assertNotNull(movie.getActorList());
        Assertions.assertThrows(AbstractRuntimeException.class, () -> {
            movieService.save(movie);
        });
    }

    @Test
    void Saving_new_movie_with_valid_request_and_valid_actor_list_is_valid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Actor actor = new Actor();

        Movie movie = new Movie();
        movie.setId(testId);
        movie.setDescription("Inception");
        movie.setProductionCost("160M USD");
        movie.setYearReleased("2010");
        movie.setImageDirectory("/directory/do_not_delete.jpg");
        movie.setActorList(Set.of(actor));

        Mockito.when(actorProxy.findAll()).thenReturn(Arrays.asList(actor));
        Mockito.when(movieDao.save(movie)).thenReturn(movie);
        Assertions.assertNotNull(movie.getActorList());
        Assertions.assertEquals(movieService.save(movie), testId);
    }

    @Test
    void Saving_new_movie_with_valid_request_and_valid_actor_list_but_empty_is_valid() {
        String testId = "4028c4ec843e0c1d0184410605070001";

        Actor actor = new Actor();

        Movie movie = new Movie();
        movie.setId(testId);
        movie.setDescription("Inception");
        movie.setProductionCost("160M USD");
        movie.setYearReleased("2010");
        movie.setImageDirectory("/directory/do_not_delete.jpg");
        movie.setActorList(new HashSet<Actor>());

        Mockito.when(actorProxy.findAll()).thenReturn(Arrays.asList(actor));
        Mockito.when(movieDao.save(movie)).thenReturn(movie);
        Assertions.assertNotNull(movie.getActorList());
        Assertions.assertEquals(movieService.save(movie), testId);
    }

    @Test
    void Updating_a_movie_with_valid_id_and_valid_request_is_valid() {
        String testId = "4028c4ec84419c76018441a921e80001";

        MovieRequestModel movieRequest = new MovieRequestModel();
        movieRequest.setId(testId);
        movieRequest.setProductionCost("Production Cost");
        movieRequest.setImageDirectory("Image Directory");

        Mockito.when(movieDao.findById(testId)).thenReturn(Optional.of(new Movie()));
        Assertions.assertNotNull(movieRequest);
        Assertions.assertTrue(movieService.update(testId, movieRequest));
    }

    @Test
    void Updating_a_movie_with_valid_id_and_null_request_values_will_only_retain() {
        String testId = "4028c4ec84419c76018441a921e80001";

        MovieRequestModel movieRequest = new MovieRequestModel();
        movieRequest.setId(testId);
        movieRequest.setProductionCost(null);
        movieRequest.setImageDirectory(null);

        Mockito.when(movieDao.findById(testId)).thenReturn(Optional.of(new Movie()));
        Assertions.assertTrue(movieService.update(testId, movieRequest));
    }

    @Test
    void Updating_a_movie_with_invalid_id_is_invalid() {
        String testId = "testId";

        MovieRequestModel movieRequest = new MovieRequestModel();
        movieRequest.setId(testId);
        movieRequest.setProductionCost("Production Cost");
        movieRequest.setImageDirectory("Image Directory");

        Mockito.when(movieDao.findById(testId)).thenReturn(Optional.empty());
        Assertions.assertThrows(AbstractRuntimeException.class, () -> {
            movieService.update(testId, movieRequest);
        });
    }

    @Test
    void Find_all_existing_movies() {
        String testId = "4028c4ec84419c76018441a921e80001";

        Actor actor = new Actor();
        actor.setId("4028c4ec843e0c1d0184410605070001");
        actor.setAge("47");
        actor.setFirstName("Leonardo");
        actor.setLastName("DiCaprio");
        actor.setGender("M");

        Movie movie = new Movie();
        movie.setId(testId);
        movie.setDescription("Inception");
        movie.setProductionCost("160M USD");
        movie.setYearReleased("2010");
        movie.setImageDirectory("/directory/do_not_delete.jpg");
        movie.setActorList(Set.of(actor));

        Mockito.when(movieDao.findAll()).thenReturn(Arrays.asList(movie));
        Assertions.assertEquals(movieService.findAll(), Arrays.asList(movie));
    }

    @Test
    void Deleting_a_movie_with_valid_id_is_valid() {
        String testId = "4028c4ec84419c76018441a921e80001";

        Movie movie = new Movie();
        movie.setId(testId);
        movie.setYearReleased("2010");

        Mockito.when(movieDao.findById(testId)).thenReturn(Optional.of(movie));
        Assertions.assertTrue(LocalDate.now().getYear() - 2010 > 1);
        Assertions.assertTrue(movieService.deleteById(testId));
    }

    @Test
    void Deleting_a_movie_with_valid_id_and_invalid_date_is_invalid() {
        String testId = "4028c4ec84419c76018441a921e80001";

        Movie movie = new Movie();
        movie.setId(testId);
        movie.setYearReleased("2022");

        Mockito.when(movieDao.findById(testId)).thenReturn(Optional.of(movie));
        Assertions.assertFalse(LocalDate.now().getYear() - 2022 > 1);
        Assertions.assertThrows(AbstractRuntimeException.class, () -> {
            movieService.deleteById(testId);
        });
    }

    @Test
    void Deleting_a_movie_with_invalid_id_is_invalid() {
        String testId = "testId";

        Movie movie = new Movie();
        movie.setId(testId);
        movie.setYearReleased("2010");

        Mockito.when(movieDao.findById(testId)).thenReturn(Optional.empty());
        Assertions.assertThrows(AbstractRuntimeException.class, () -> {
            movieService.deleteById(testId);
        });
    }
}
