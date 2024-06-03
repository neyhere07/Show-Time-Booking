package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.Entities.Format;
import com.project.ShowTimeBooking.Entities.Genre;
import com.project.ShowTimeBooking.Entities.Language;
import com.project.ShowTimeBooking.Entities.Profession;
import com.project.ShowTimeBooking.Enums.FormatEnum;
import com.project.ShowTimeBooking.Enums.GenreEnum;
import com.project.ShowTimeBooking.Enums.LanguageEnum;
import com.project.ShowTimeBooking.Enums.ProfessionEnum;

import java.util.List;

public interface GeneralService {
    Object addLanguage(List<LanguageEnum> languageEnumList) throws Exception;
    List<Language> getLanguageList(List<LanguageEnum> languageEnumList);

    Object addGenre(List<GenreEnum> genreEnumList) throws Exception;
    List<Genre> getGenreList(List<GenreEnum> genreEnumList);

    Object addFormat(List<FormatEnum> formatEnumList) throws Exception;
    List<Format> getFormatList(List<FormatEnum> formatEnumList);

    Object addProfession(List<ProfessionEnum> professionEnumList) throws Exception;
    List<Profession> getProfessionList(List<ProfessionEnum> professionEnumList);
}
