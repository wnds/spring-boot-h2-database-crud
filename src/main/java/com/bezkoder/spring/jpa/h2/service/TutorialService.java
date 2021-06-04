package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.model.Tutorial;
import com.bezkoder.spring.jpa.h2.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    public List<Tutorial> getAllTutorials(String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

        return tutorials;
    }

    public Optional<Tutorial> findById(long id) {
        return tutorialRepository.findById(id);
    }

    public Tutorial save(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public Tutorial update(long id, Tutorial tutorial) throws Exception {

        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return tutorialRepository.save(tutorial);
        }
        throw new Exception("Not found");
    }

    public void deleteById(long id) {
        tutorialRepository.deleteById(id);
    }

    public void deleteAll() {
        tutorialRepository.deleteAll();
    }

    public List<Tutorial> findByPublished(boolean b) {
        return tutorialRepository.findByPublished(b);
    }
}
