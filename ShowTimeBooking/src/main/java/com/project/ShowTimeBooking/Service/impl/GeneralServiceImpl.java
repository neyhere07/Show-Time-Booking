package com.project.ShowTimeBooking.Service.impl;

import com.project.ShowTimeBooking.Entities.Format;
import com.project.ShowTimeBooking.Entities.Genre;
import com.project.ShowTimeBooking.Entities.Language;
import com.project.ShowTimeBooking.Entities.Profession;
import com.project.ShowTimeBooking.Enums.FormatEnum;
import com.project.ShowTimeBooking.Enums.GenreEnum;
import com.project.ShowTimeBooking.Enums.LanguageEnum;
import com.project.ShowTimeBooking.Enums.ProfessionEnum;
import com.project.ShowTimeBooking.Exceptions.FormatAlreadyPresentException;
import com.project.ShowTimeBooking.Exceptions.GenreAlreadyPresentException;
import com.project.ShowTimeBooking.Exceptions.LanguageAlreadyPresentException;
import com.project.ShowTimeBooking.Exceptions.ProfessionAlreadyPresentException;
import com.project.ShowTimeBooking.Repository.FormatRepository;
import com.project.ShowTimeBooking.Repository.GenreRepository;
import com.project.ShowTimeBooking.Repository.LanguageRepository;
import com.project.ShowTimeBooking.Repository.ProfessionRepository;
import com.project.ShowTimeBooking.Service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GeneralServiceImpl implements GeneralService {
    @Autowired
    private FormatRepository formatRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ProfessionRepository professionRepository;


    public Object addLanguage(List<LanguageEnum> languageEnumList)throws Exception{
        List<String>alreadyPresentLanguageList=new ArrayList<>();
        List<String>alreadyNotPresentLanguageList=new ArrayList<>();
        for(LanguageEnum languageEnum: languageEnumList){
            Optional<Language> optionalLanguage=languageRepository.findByName(languageEnum);
            if(!optionalLanguage.isPresent()){
                Language language=new Language();
                language.setName(languageEnum);
                languageRepository.save(language);
                alreadyNotPresentLanguageList.add(languageEnum.getDisplayName());
            }else{
                alreadyPresentLanguageList.add(languageEnum.getDisplayName());
            }
        }
        if(alreadyPresentLanguageList.size()!=0){
            throw new LanguageAlreadyPresentException("Cannot add. Language(s) "+alreadyPresentLanguageList+" is/are present in the database");
        }else{
            return "language(s) "+alreadyNotPresentLanguageList+" added to the database successfully";
        }
    }
    public List<Language> getLanguageList(List<LanguageEnum> languageEnumList) {
        return languageRepository.findByNameIn(languageEnumList);
    }

    public Object addGenre(List<GenreEnum> genreEnumList)throws Exception{
        List<String> alreadyPresentGenreList = new ArrayList<>();
        List<String> alreadyNotPresentGenreList = new ArrayList<>();

        for (GenreEnum genreEnum : genreEnumList) {
            Optional<Genre> optionalGenre = genreRepository.findByName(genreEnum);

            if (optionalGenre.isEmpty()) {
                Genre genre = new Genre();
                genre.setName(genreEnum);
                genreRepository.save(genre);
                alreadyNotPresentGenreList.add(genreEnum.getDisplayName());
            } else {
                alreadyPresentGenreList.add(genreEnum.getDisplayName());
            }
        }

        if (!alreadyPresentGenreList.isEmpty()) {
            throw new GenreAlreadyPresentException("Cannot add. Genre(s) " + alreadyPresentGenreList + " is/are present in the database");
        } else {
            return "Genre(s) " + alreadyNotPresentGenreList + " added to the database successfully";
        }
    }
    public List<Genre> getGenreList(List<GenreEnum> genreEnumList){
        List<Genre>genreList=genreRepository.findByNameIn(genreEnumList);
        return genreList;
    }

    public Object addFormat(List<FormatEnum> formatEnumList)throws Exception{
        List<String> alreadyPresentFormatList = new ArrayList<>();
        List<String> alreadyNotPresentFormatList = new ArrayList<>();

        for (FormatEnum formatEnum : formatEnumList) {
            Optional<Format> optionalFormat = formatRepository.findByName(formatEnum);

            if (optionalFormat.isEmpty()) {
                Format format = new Format();
                format.setName(formatEnum);
                formatRepository.save(format);
                alreadyNotPresentFormatList.add(formatEnum.getDisplayName());
            } else {
                alreadyPresentFormatList.add(formatEnum.getDisplayName());
            }
        }

        if (!alreadyPresentFormatList.isEmpty()) {
            throw new FormatAlreadyPresentException("Cannot add. Format(s) " + alreadyPresentFormatList + " is/are present in the database");
        } else {
            return "Format(s) " + alreadyNotPresentFormatList + " added to the database successfully";
        }
    }
    public List<Format> getFormatList(List<FormatEnum> formatEnumList) {
        return formatRepository.findByNameIn(formatEnumList);
    }


    public Object addProfession(List<ProfessionEnum> professionEnumList)throws Exception{
        List<String> alreadyPresentProfessionList = new ArrayList<>();
        List<String> alreadyNotPresentProfessionList = new ArrayList<>();

        for (ProfessionEnum professionEnum : professionEnumList) {
            Optional<Profession> optionalProfession = professionRepository.findByName(professionEnum);

            if (optionalProfession.isEmpty()) {
                Profession profession = new Profession();
                profession.setName(professionEnum);
                professionRepository.save(profession);
                alreadyNotPresentProfessionList.add(professionEnum.getDisplayName());
            } else {
                alreadyPresentProfessionList.add(professionEnum.getDisplayName());
            }
        }

        if (!alreadyPresentProfessionList.isEmpty()) {
            throw new ProfessionAlreadyPresentException("Cannot add. Profession(s) " + alreadyPresentProfessionList + " is/are present in the database");
        } else {
            return "Profession(s) " + alreadyNotPresentProfessionList + " added to the database successfully";
        }
    }
    public List<Profession> getProfessionList(List<ProfessionEnum> professionEnumList) {
        return professionRepository.findByNameIn(professionEnumList);
    }
}
