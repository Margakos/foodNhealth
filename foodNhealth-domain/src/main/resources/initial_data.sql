-- Insert Initial Data to the System

/* Cuisines */
INSERT INTO foodnhealth.Cuisine (id, description, isActive, isDeleted, title) VALUES (1, '', true, false, 'Basic');
INSERT INTO foodnhealth.Cuisine (id, description, isActive, isDeleted, title) VALUES (2, '', true, false, 'Vegetarian');
INSERT INTO foodnhealth.Cuisine (id, description, isActive, isDeleted, title) VALUES (3, '', true, false, 'Vegan');
INSERT INTO foodnhealth.Cuisine (id, description, isActive, isDeleted, title) VALUES (4, '', true, false, 'Mediterranean');
INSERT INTO foodnhealth.Cuisine (id, description, isActive, isDeleted, title) VALUES (5, '', true, false, 'Asian');

/* Recipe Categories */
INSERT INTO foodnhealth.RecipeCategory (id, description, isActive, isDeleted, title) VALUES (1, '', true, false, 'Μικρά γεύματα');
INSERT INTO foodnhealth.RecipeCategory (id, description, isActive, isDeleted, title) VALUES (2, '', true, false, 'Κυρίως γεύματα');
INSERT INTO foodnhealth.RecipeCategory (id, description, isActive, isDeleted, title) VALUES (3, '', true, false, 'Ροφήματα');
INSERT INTO foodnhealth.RecipeCategory (id, description, isActive, isDeleted, title) VALUES (4, '', true, false, 'Σνακ');
INSERT INTO foodnhealth.RecipeCategory (id, description, isActive, isDeleted, title) VALUES (5, '', true, false, 'Συνοδευτικά');
INSERT INTO foodnhealth.RecipeCategory (id, description, isActive, isDeleted, title) VALUES (6, '', true, false, 'Φρούτα');
INSERT INTO foodnhealth.RecipeCategory (id, description, isActive, isDeleted, title) VALUES (7, '', true, false, 'Σαλάτες');

/* SuperMarket */
INSERT INTO foodnhealth.Supermarket (id, description, isActive, isDeleted, title) VALUES (1, '', true, false, 'AB');
INSERT INTO foodnhealth.Supermarket (id, description, isActive, isDeleted, title) VALUES (2, '', true, false, 'Masoutis');
INSERT INTO foodnhealth.Supermarket (id, description, isActive, isDeleted, title) VALUES (3, '', true, false, 'Lidl');

/* Food Category Core Types */
INSERT INTO foodnhealth.FoodCategoryCoreType (id, description, isActive, isDeleted, title) VALUES (1, '', true, false, 'Γαλακτοκομικά');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, description, isActive, isDeleted, title) VALUES (2, '', true, false, 'Λαχανικά');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, description, isActive, isDeleted, title) VALUES (3, '', true, false, 'Φρούτα κ Χυμοί');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, description, isActive, isDeleted, title) VALUES (4, '', true, false, 'Αμυλούχα');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, description, isActive, isDeleted, title) VALUES (5, '', true, false, 'Κρέατα');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, description, isActive, isDeleted, title) VALUES (6, '', true, false, 'Λίποι');
INSERT INTO foodnhealth.FoodCategoryCoreType (id, description, isActive, isDeleted, title) VALUES (7, '', true, false, 'Ελεύθερα');

/* Food Category Sub Types */
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (1, '', true, false, 'Πλήρες', 1);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (2, '', true, false, 'Ημίπαχα', 1);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (3, '', true, false, 'Άπαχα', 1);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (4, '', true, false, 'Λαχανικά', 2);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (5, '', true, false, 'Φρούτα', 3);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (6, '', true, false, 'Χυμοί', 3);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (7, '', true, false, 'Ψωμί', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (8, '', true, false, 'Δημητριακά', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (9, '', true, false, 'Ρύζι', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (10, '', true, false, 'Σούπες', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (11, '', true, false, 'Όσπρια', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (12, '', true, false, 'Αμυλούχα λαχανικά', 4);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (13, '', true, false, 'Χαμηλό σε λιπαρά κρέας', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (14, '', true, false, 'Χαμηλό σε λιπαρά τυρί', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (15, '', true, false, 'Χαμηλό σε λιπαρά ψάρι', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (16, '', true, false, 'Μέσο σε λιπαρά κρέας', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (17, '', true, false, 'Μέσο σε λιπαρά τυρί', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (18, '', true, false, 'Μέσο σε λιπαρά ψάρι', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (19, '', true, false, 'Μέσο σε λιπαρά αυγό', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (20, '', true, false, 'Υψηλό σε λιπαρά κρέας', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (21, '', true, false, 'Υψηλό σε λιπαρά τυρί', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (22, '', true, false, 'Υψηλό σε λιπαρά ψάρι', 5);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (23, '', true, false, 'Κορεσμένα', 6);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (24, '', true, false, 'Μονοακόρεστα', 6);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (25, '', true, false, 'Μολυακόρεστα', 6);
INSERT INTO foodnhealth.FoodCategorySubType (id, description, isActive, isDeleted, title, foodCategoryCoreType_id) VALUES (26, '', true, false, 'Ελεύθερα', 7);

/* Users */
INSERT INTO foodnhealth.Nutritionist (id, description, isActive, isDeleted, title, comments, email, firstName, gender, lastName, password)
VALUES (1, 'The first ever admin', true, false, null, 'First admin ever', 'admin@foodnhealth.gr', 'Food', null, 'Health', '123456');