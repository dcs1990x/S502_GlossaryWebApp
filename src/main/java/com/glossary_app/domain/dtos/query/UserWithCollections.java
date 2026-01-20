package com.glossary_app.domain.dtos.query;

import com.glossary_app.domain.model.Collection;
import com.glossary_app.domain.model.User;
import java.util.List;

public record UserWithCollections(
        User user,
        List<Collection> collections
) {}