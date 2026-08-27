package com.example.data.model

object FoodCatalog {
    val items: List<FoodItem> = listOf(
        // CAKES SECTION
        FoodItem(
            id = "cake_butter_1kg",
            nameEn = "Butter Cake",
            nameSi = "බටර් කේක්",
            category = FoodCategory.CAKES,
            basePrice = 1200.0,
            unit = "1kg",
            description = "Rich, moist, and aromatic traditional golden Sri Lankan butter cake made with pure creamery butter.",
            sinhalaDescription = "නියම බටර් සුවඳින් සහ රසයෙන් අනූන නැවුම් බටර් කේක්.",
            isFeatured = true,
            isBestSeller = true,
            options = listOf(
                ProductOption("1 kg Standard", "කිලෝ 1", 1200.0, "1kg"),
                ProductOption("1.5 kg Medium", "කිලෝ 1.5", 1800.0, "1.5kg"),
                ProductOption("2 kg Family Pack", "කිලෝ 2", 2400.0, "2kg")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_chocolate_1kg",
            nameEn = "Chocolate Cake",
            nameSi = "චොක්ලට් කේක්",
            category = FoodCategory.CAKES,
            basePrice = 1300.0,
            unit = "1kg",
            description = "Decadent Dutch cocoa infused soft sponge cake layered with rich chocolate fudge.",
            sinhalaDescription = "නියම කොකෝවා රසැති මෘදු රසවත් චොක්ලට් කේක්.",
            isFeatured = true,
            isBestSeller = true,
            options = listOf(
                ProductOption("1 kg Standard", "කිලෝ 1", 1300.0, "1kg"),
                ProductOption("1.5 kg Medium", "කිලෝ 1.5", 1950.0, "1.5kg"),
                ProductOption("2 kg Family Pack", "කිලෝ 2", 2600.0, "2kg")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_ribbon_1kg",
            nameEn = "Ribbon Cake",
            nameSi = "රිබන් කේක්",
            category = FoodCategory.CAKES,
            basePrice = 1300.0,
            unit = "1kg",
            description = "Classic festive tricolor layered sponge cake sandwiched with luscious vanilla icing.",
            sinhalaDescription = "උත්සව අවස්ථාවන්ට සුදුසු ලස්සන වර්ණවත් රිබන් කේක්.",
            isFeatured = false,
            options = listOf(
                ProductOption("1 kg Standard", "කිලෝ 1", 1300.0, "1kg"),
                ProductOption("2 kg Large", "කිලෝ 2", 2600.0, "2kg")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_banana_1kg",
            nameEn = "Banana Cake",
            nameSi = "කෙසෙල් කේක්",
            category = FoodCategory.CAKES,
            basePrice = 1400.0,
            unit = "1kg",
            description = "Naturally sweetened fresh banana loaf cake with subtle cinnamon and vanilla notes.",
            sinhalaDescription = "නැවුම් කෙසෙල් සුවඳින් පිරි ස්වභාවික රසැති කෙසෙල් කේක්.",
            options = listOf(
                ProductOption("1 kg Loaf", "කිලෝ 1", 1400.0, "1kg")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_coconut_1kg",
            nameEn = "Coconut Cake",
            nameSi = "පොල් කේක්",
            category = FoodCategory.CAKES,
            basePrice = 1100.0,
            unit = "1kg",
            description = "Traditional toasted coconut infused soft cake, delicate and wonderfully fragrant.",
            sinhalaDescription = "දේශීය පොල් රසයෙන් පිරි රසවත් පොල් කේක්.",
            options = listOf(
                ProductOption("1 kg Standard", "කිලෝ 1", 1100.0, "1kg")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_kurakkan_1kg",
            nameEn = "Kurakkan Flour Cake",
            nameSi = "කුරක්කන් පිටි කේක්",
            category = FoodCategory.CAKES,
            basePrice = 1400.0,
            unit = "1kg",
            description = "Healthy wholesome finger millet cake, nutritious and mildly spiced for a guilt-free treat.",
            sinhalaDescription = "පෝෂණ ගුණයෙන් ඉහළ සෞඛ්‍යාරක්ෂිත කුරක්කන් පිටි කේක්.",
            options = listOf(
                ProductOption("1 kg Health Pack", "කිලෝ 1", 1400.0, "1kg")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_rice_flour_1kg",
            nameEn = "Rice Flour Cake",
            nameSi = "හාල් පිටි කේක්",
            category = FoodCategory.CAKES,
            basePrice = 1400.0,
            unit = "1kg",
            description = "Authentic heritage recipe made with fine roasted rice flour and kithul treacle hints.",
            sinhalaDescription = "පාරම්පරික ක්‍රමයට සකස් කළ ගුණදායක හාල් පිටි කේක්.",
            options = listOf(
                ProductOption("1 kg Heritage Pack", "කිලෝ 1", 1400.0, "1kg")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_fruit_1kg",
            nameEn = "Fruit Cake",
            nameSi = "ෆෘට් කේක්",
            category = FoodCategory.CAKES,
            basePrice = 1400.0,
            unit = "1kg",
            description = "Loaded with premium candied peel, sultanas, raisins and fragrant warming spices.",
            sinhalaDescription = "වියළි මිදි, කජු සහ පලතුරු පිරි රසවත් ෆෘට් කේක්.",
            isFeatured = true,
            options = listOf(
                ProductOption("1 kg Full Rich", "කිලෝ 1", 1400.0, "1kg")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_marble_1kg",
            nameEn = "Marble Cake",
            nameSi = "මාබල් කේක්",
            category = FoodCategory.CAKES,
            basePrice = 1300.0,
            unit = "1kg",
            description = "Artfully swirled rich vanilla and dark chocolate duo sponge.",
            sinhalaDescription = "වැනිලා සහ චොක්ලට් මිශ්‍රණයෙන් කළ මාබල් කේක්.",
            options = listOf(
                ProductOption("1 kg Standard", "කිලෝ 1", 1300.0, "1kg")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_watalappan",
            nameEn = "Watalappan",
            nameSi = "වටලප්පන්",
            category = FoodCategory.CAKES,
            basePrice = 150.0,
            unit = "Cup / 1kg",
            description = "Authentic Sri Lankan steamed jaggery and coconut milk pudding topped with crunchy cashew nuts and cardamom.",
            sinhalaDescription = "කිතුල් හකුරු සහ පොල් කිරිවලින් සෑදූ රසවත් සාම්ප්‍රදායික වටලප්පන්.",
            isFeatured = true,
            isBestSeller = true,
            options = listOf(
                ProductOption("Individual Serving Cup", "කප් 1", 150.0, "Cup"),
                ProductOption("1 kg Large Clay Dish / Bowl", "කිලෝ 1 මැටි බඳුන", 1500.0, "1kg")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_clay_pot",
            nameEn = "Clay Pot Cake (මුට්ටි කේක්)",
            nameSi = "මුට්ටි කේක්",
            category = FoodCategory.CAKES,
            basePrice = 350.0,
            unit = "Pot",
            description = "Specialty homemade cake baked directly inside traditional Sri Lankan clay pots for signature aroma and crust.",
            sinhalaDescription = "ස්වභාවික මැටි මුට්ටියේ පිළිස්සූ විශේෂ මුට්ටි කේක්.",
            isFeatured = true,
            isBestSeller = true,
            options = listOf(
                ProductOption("Small Pot", "කුඩා මුට්ටිය", 350.0, "Small"),
                ProductOption("Medium Pot", "මධ්‍යම මුට්ටිය", 500.0, "Medium"),
                ProductOption("Large Celebration Pot", "විශාල මුට්ටිය", 1000.0, "Large")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_cupcakes",
            nameEn = "Cup Cakes",
            nameSi = "කප් කේක්",
            category = FoodCategory.CAKES,
            basePrice = 150.0,
            unit = "1 pc",
            description = "Fluffy mini sponge cakes available in classic or decorated festive icing.",
            sinhalaDescription = "ලස්සන සැරසිලි සහිත නැවුම් කප් කේක්.",
            options = listOf(
                ProductOption("Standard Classic (1 pc)", "සාමාන්‍ය (1 pc)", 150.0, "1 pc"),
                ProductOption("Premium Decorated / Icing (1 pc)", "විශේෂ සැරසිලි (1 pc)", 250.0, "1 pc")
            ),
            imageDrawableName = "img_cake_category"
        ),
        FoodItem(
            id = "cake_lava",
            nameEn = "Lava Cake",
            nameSi = "ලාවා කේක්",
            category = FoodCategory.CAKES,
            basePrice = 150.0,
            unit = "1 pc",
            description = "Warm chocolate fondant cake with molten chocolate river core.",
            sinhalaDescription = "දියවන උණුසුම් චොක්ලට් පිරි ලාවා කේක්.",
            isFeatured = true,
            options = listOf(
                ProductOption("1 pc Single Lava Cake", "කෑල්ල 1", 150.0, "1 pc")
            ),
            imageDrawableName = "img_cake_category"
        ),

        // TRADITIONAL SWEETMEATS & SNACKS
        FoodItem(
            id = "sweet_kokis_large",
            nameEn = "Kokis (Large)",
            nameSi = "කොකිස් (ලොකු)",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 30.0,
            unit = "1 pc",
            description = "Crisp, golden flower-shaped traditional rice flour sweet fritter.",
            sinhalaDescription = "කර කර ගාලා හැපෙන රන්වන් පැහැති රසවත් ලොකු කොකිස්.",
            isFeatured = true,
            isBestSeller = true,
            options = listOf(
                ProductOption("1 pc", "1 pc", 30.0, "1 pc"),
                ProductOption("Pack of 10 pcs", "10 ක පැකට්ටුව", 300.0, "10 pcs"),
                ProductOption("Party Box (50 pcs)", "50 ක පෙට්ටිය", 1500.0, "50 pcs")
            ),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_kokis_small",
            nameEn = "Kokis (Small)",
            nameSi = "කොකිස් (පොඩි)",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 25.0,
            unit = "1 pc",
            description = "Crispy bite-sized traditional festive kokis.",
            sinhalaDescription = "පොඩි ප්‍රමාණයේ කර කර ගාන රසැති කොකිස්.",
            options = listOf(
                ProductOption("1 pc", "1 pc", 25.0, "1 pc"),
                ProductOption("Pack of 10 pcs", "10 ක පැකට්ටුව", 250.0, "10 pcs"),
                ProductOption("Party Box (50 pcs)", "50 ක පෙට්ටිය", 1250.0, "50 pcs")
            ),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_konda_kavum",
            nameEn = "Konda Kavum",
            nameSi = "කොණ්ඩ කැවුම්",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 50.0,
            unit = "1 pc",
            description = "Iconic Sri Lankan oil cake prepared with pure treacle and rice flour, with beautiful golden top knot.",
            sinhalaDescription = "නියම කිතුල් පැණි සුවඳින් පිසූ සම්ප්‍රදායික කොණ්ඩ කැවුම්.",
            isFeatured = true,
            isBestSeller = true,
            options = listOf(
                ProductOption("1 pc", "1 pc", 50.0, "1 pc"),
                ProductOption("Pack of 10 pcs", "10 ක පැකට්ටුව", 500.0, "10 pcs"),
                ProductOption("Event Tray (50 pcs)", "50 ක තැටිය", 2500.0, "50 pcs")
            ),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_mung_kavum",
            nameEn = "Mung Kavum",
            nameSi = "මුං කැවුම්",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 40.0,
            unit = "1 pc",
            description = "Diamond-shaped yellow mung bean cake deep-fried with crunchy batter and sweet interior.",
            sinhalaDescription = "මුං ඇට පිටි සහ පැණියෙන් සාදන ලද රසවත් මුං කැවුම්.",
            isFeatured = true,
            options = listOf(
                ProductOption("1 pc", "1 pc", 40.0, "1 pc"),
                ProductOption("Pack of 10 pcs", "10 ක පැකට්ටුව", 400.0, "10 pcs")
            ),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_dodol",
            nameEn = "Kalu Dodol",
            nameSi = "කළු දොදොල්",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 1300.0,
            unit = "1kg",
            description = "Dark, rich, chewy Sri Lankan delicacy made by slow-stirring coconut milk, kithul jaggery and roasted cashews.",
            sinhalaDescription = "කජු සහ පැණි පිරි අව්‍යාජ කළු දොදොල්.",
            isFeatured = true,
            isBestSeller = true,
            options = listOf(
                ProductOption("500g Pack", "ග්‍රෑම් 500", 650.0, "500g"),
                ProductOption("1 kg Block", "කිලෝ 1", 1300.0, "1kg"),
                ProductOption("2 kg Gift Box", "කිලෝ 2 පෙට්ටිය", 2600.0, "2kg")
            ),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_handi_kavum",
            nameEn = "Handi Kavum",
            nameSi = "හැඳි කැවුම්",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 45.0,
            unit = "1 pc",
            description = "Traditional spoon-poured soft treacle cakes with caramelized crispy edges.",
            sinhalaDescription = "පැණි බේරෙන මෘදු රසවත් හැඳි කැවුම්.",
            options = listOf(ProductOption("1 pc", "1 pc", 45.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_naran_kavum",
            nameEn = "Naran Kavum",
            nameSi = "නාරං කැවුම්",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 45.0,
            unit = "1 pc",
            description = "Crispy golden ball pastry encasing a sweet shredded coconut, treacle and spice filling.",
            sinhalaDescription = "පැණි පොල් පිරවුම සහිත නාරං කැවුම්.",
            options = listOf(ProductOption("1 pc", "1 pc", 45.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_athirasa",
            nameEn = "Athirasa",
            nameSi = "අතිරස",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 45.0,
            unit = "1 pc",
            description = "Ultra-rich flat sweetmeat with aromatic roasted spices, jaggery and rice flour.",
            sinhalaDescription = "පැණි රසයෙන් පිරි රසවත් අතිරස.",
            options = listOf(ProductOption("1 pc", "1 pc", 45.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_aasmi",
            nameEn = "Aasmi",
            nameSi = "ආස්මි",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 45.0,
            unit = "1 pc",
            description = "Lacy, nest-like crispy rice flour and cinnamon leaf delicacy drizzled with bright pink sugar syrup.",
            sinhalaDescription = "රෝස පාට පැණි ඉසින ලද හැපෙන ආස්මි.",
            isFeatured = true,
            options = listOf(ProductOption("1 pc", "1 pc", 45.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_welithalapa",
            nameEn = "Welithalapa",
            nameSi = "වැලිතලප",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 50.0,
            unit = "1 pc",
            description = "Steamed spiced flour granules gently simmered in boiling kithul treacle and pressed into soft diamonds.",
            sinhalaDescription = "නියම කිතුල් පැණියෙන් හැදූ වැලිතලප.",
            options = listOf(ProductOption("1 pc", "1 pc", 50.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_undu_walalu",
            nameEn = "Undu Walalu (Pani Walalu)",
            nameSi = "උඳු වළලු (පැණි වළලු)",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 30.0,
            unit = "1 pc",
            description = "Coiled urad dal rings soaked in thick fragrant sugar-treacle syrup.",
            sinhalaDescription = "පැණි බේරෙන උණුසුම් උඳු වළලු.",
            isFeatured = true,
            options = listOf(ProductOption("1 pc", "1 pc", 30.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_kiri_toffee",
            nameEn = "Kiri Toffee (Milk Toffee)",
            nameSi = "කිරි ටොෆි",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 15.0,
            unit = "1 pc",
            description = "Melt-in-your-mouth condensed milk fudge squares with cashew nut crunch and vanilla aroma.",
            sinhalaDescription = "කිරි සහ කජු මුසු මෘදු කිරි ටොෆි.",
            isFeatured = true,
            isBestSeller = true,
            options = listOf(
                ProductOption("1 pc", "1 pc", 15.0, "1 pc"),
                ProductOption("Pack of 20 pcs", "20 ක පැකට්ටුව", 300.0, "20 pcs"),
                ProductOption("Jar of 50 pcs", "50 ක බෝතලය", 750.0, "50 pcs")
            ),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_pol_toffee",
            nameEn = "Pol Toffee (Coconut Toffee)",
            nameSi = "පොල් ටොෆි",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 15.0,
            unit = "1 pc",
            description = "Chewy, colorful coconut fudge candy bars with sugar syrup and cardamom.",
            sinhalaDescription = "පොල් සහ සීනි මිශ්‍ර රසැති පොල් ටොෆි.",
            options = listOf(ProductOption("1 pc", "1 pc", 15.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_rulan_toffee",
            nameEn = "Rulan Toffee (Semolina Toffee)",
            nameSi = "රුලං ටොෆි",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 15.0,
            unit = "1 pc",
            description = "Roasted semolina and butter confection flavored with rose water and cashews.",
            sinhalaDescription = "බටර් සහ රුලං මිශ්‍ර රසවත් රුලං ටොෆි.",
            options = listOf(ProductOption("1 pc", "1 pc", 15.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_laddu",
            nameEn = "Laddu",
            nameSi = "ලඩ්ඩු",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 60.0,
            unit = "1 pc",
            description = "Golden aromatic round sweet prepared with ghee, gram flour pearls, and cashews.",
            sinhalaDescription = "එළඟිතෙල් සහ කජු පිරි රසවත් ලඩ්ඩු.",
            options = listOf(ProductOption("1 pc", "1 pc", 60.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_aluwa",
            nameEn = "Aluwa",
            nameSi = "අලුවා",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 40.0,
            unit = "1 pc",
            description = "Traditional soft roasted rice flour diamond fudge prepared with treacle and crushed cashews.",
            sinhalaDescription = "කජු සහ පැණි රසැති සම්ප්‍රදායික අලුවා.",
            options = listOf(ProductOption("1 pc", "1 pc", 40.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_boondi",
            nameEn = "Boondi",
            nameSi = "බූන්දි",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 1200.0,
            unit = "1kg",
            description = "Sweet, colorful tiny crispy gram flour drops coated in infused sugar syrup.",
            sinhalaDescription = "පැණි තැවරූ රසවත් වර්ණවත් බූන්දි.",
            options = listOf(
                ProductOption("500g Pack", "ග්‍රෑම් 500", 600.0, "500g"),
                ProductOption("1 kg Pack", "කිලෝ 1", 1200.0, "1kg")
            ),
            imageDrawableName = "img_sweets_category"
        ),
        FoodItem(
            id = "sweet_halapa",
            nameEn = "Halapa",
            nameSi = "හැලප",
            category = FoodCategory.TRADITIONAL_SWEETS,
            basePrice = 60.0,
            unit = "1 pc",
            description = "Wholesome kurakkan dough stuffed with sweet scraped coconut and steamed inside fresh Kenda leaves.",
            sinhalaDescription = "කැන්ද කොළයේ ඔතා තැම්බූ පැණි පොල් පිරි රසවත් හැලප.",
            options = listOf(ProductOption("1 pc", "1 pc", 60.0, "1 pc")),
            imageDrawableName = "img_sweets_category"
        ),

        // SAVORIES & SNACKS
        FoodItem(
            id = "snack_salmon_cutlet",
            nameEn = "Salmon Cutlet",
            nameSi = "සැමන් කට්ලට්",
            category = FoodCategory.SAVORIES_SNACKS,
            basePrice = 80.0,
            unit = "1 pc",
            description = "Spicy wild salmon, potato, black pepper, and green chili filling coated in golden crispy breadcrumbs.",
            sinhalaDescription = "නියම සැමන් සහ අල පිරවුම සහිත සැර කට්ලට්.",
            isFeatured = true,
            isBestSeller = true,
            options = listOf(
                ProductOption("1 pc", "1 pc", 80.0, "1 pc"),
                ProductOption("Pack of 10 pcs", "10 ක පැකට්ටුව", 800.0, "10 pcs"),
                ProductOption("Party Box (30 pcs)", "30 ක පෙට්ටිය", 2400.0, "30 pcs")
            ),
            imageDrawableName = "img_hero_banner"
        ),
        FoodItem(
            id = "snack_egg_rolls",
            nameEn = "Egg Rolls (Chinese Rolls)",
            nameSi = "බිත්තර රෝල්ස්",
            category = FoodCategory.SAVORIES_SNACKS,
            basePrice = 80.0,
            unit = "1 pc",
            description = "Crispy crumbed pancake roll filled with spicy egg and vegetable mixture.",
            sinhalaDescription = "බිත්තර සහ අල මිශ්‍රණය පිරවූ රසවත් රෝල්ස්.",
            isFeatured = true,
            options = listOf(
                ProductOption("1 pc", "1 pc", 80.0, "1 pc"),
                ProductOption("Pack of 10 pcs", "10 ක පැකට්ටුව", 800.0, "10 pcs")
            ),
            imageDrawableName = "img_hero_banner"
        ),
        FoodItem(
            id = "snack_kiribath",
            nameEn = "Kiribath (Milk Rice)",
            nameSi = "කිරිබත් කෑල්ල",
            category = FoodCategory.SAVORIES_SNACKS,
            basePrice = 50.0,
            unit = "1 pc",
            description = "Creamy coconut milk rice cut into traditional diamond pieces, ideal for auspicious occasions.",
            sinhalaDescription = "නැවුම් පොල් කිරිවලින් පිසූ ගුණදායක රසවත් කිරිබත්.",
            isFeatured = true,
            options = listOf(
                ProductOption("1 pc (Diamond piece)", "කෑල්ල 1", 50.0, "1 pc"),
                ProductOption("Pack of 10 pcs", "10 ක පැකට්ටුව", 500.0, "10 pcs")
            ),
            imageDrawableName = "img_hero_banner"
        ),
        FoodItem(
            id = "snack_idli",
            nameEn = "Idli",
            nameSi = "ඉඩ්ලි",
            category = FoodCategory.SAVORIES_SNACKS,
            basePrice = 60.0,
            unit = "1 pc",
            description = "Soft, fluffy steamed fermented rice and black lentil cakes.",
            sinhalaDescription = "මෘදු වාෂ්පයෙන් තැම්බූ ඉඩ්ලි.",
            options = listOf(ProductOption("1 pc", "1 pc", 60.0, "1 pc")),
            imageDrawableName = "img_hero_banner"
        ),
        FoodItem(
            id = "snack_kola_kenda_pittu",
            nameEn = "Kola Kenda / Pittu",
            nameSi = "කොළ කැඳ / පිට්ටු",
            category = FoodCategory.SAVORIES_SNACKS,
            basePrice = 30.0,
            unit = "1 serving",
            description = "Fresh herbal porridge bowl or steamed rice and coconut cylinder serving.",
            sinhalaDescription = "ඖෂධීය නැවුම් කොළ කැඳ හෝ රසවත් පිට්ටු.",
            options = listOf(
                ProductOption("Kola Kenda (Herbal Porridge)", "කොළ කැඳ කෝප්පය", 30.0, "Cup"),
                ProductOption("Pittu (Steamed Roll)", "පිට්ටු කෑල්ල", 30.0, "1 pc")
            ),
            imageDrawableName = "img_hero_banner"
        ),
        FoodItem(
            id = "snack_mukulu",
            nameEn = "Mukulu",
            nameSi = "මුකුලු",
            category = FoodCategory.SAVORIES_SNACKS,
            basePrice = 20.0,
            unit = "1 pc",
            description = "Traditional crispy savory snack bites seasoned with chili and curry leaves.",
            sinhalaDescription = "කරපිංචා සහ මිරිස් රසැති කුඩා කෙටි කෑම.",
            options = listOf(ProductOption("1 pc", "1 pc", 20.0, "1 pc")),
            imageDrawableName = "img_hero_banner"
        )
    )

    fun findById(id: String): FoodItem? = items.find { it.id == id }
}
