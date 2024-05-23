-- customers
INSERT INTO customers (name, phone, mail, address, city) VALUES ('Ahmet Yılmaz', '5551234567', 'ahmet@example.com', 'Yıldırım Mahallesi, 1234. Sokak', 'Ankara');
INSERT INTO customers (name, phone, mail, address, city) VALUES ('Ayşe Kaya', '5552345678', 'ayse@example.com', 'Güneşli Caddesi, 5678. Sokak', 'Istanbul');
INSERT INTO customers (name, phone, mail, address, city) VALUES ('Mehmet Demir', '5553456789', 'mehmet@example.com', 'Karadeniz Bulvarı, 9012. Sokak', 'Trabzon');
INSERT INTO customers (name, phone, mail, address, city) VALUES ('Fatma Şahin', '5554567890', 'fatma@example.com', 'Yıldız Mahallesi, 3456. Sokak', 'Izmir');
INSERT INTO customers (name, phone, mail, address, city) VALUES ('Mustafa Arslan', '5555678901', 'mustafa@example.com', 'Çınar Sokak, 7890. Cadde', 'Bursa');

-- veterinarians
INSERT INTO veterinarians (name, phone, mail, address, city) VALUES ('Dr. Ali Çetin', '5556781234', 'ali.cetin@example.com', 'Gazi Mahallesi, 123. Sokak', 'Ankara');
INSERT INTO veterinarians (name, phone, mail, address, city) VALUES ('Dr. Emine Şahin', '5557892345', 'emine.sahin@example.com', 'Atatürk Bulvarı, 456. Cadde', 'Istanbul');
INSERT INTO veterinarians (name, phone, mail, address, city) VALUES ('Dr. Mehmet Kara', '5558903456', 'mehmet.kara@example.com', 'İnönü Mahallesi, 789. Sokak', 'Izmir');
INSERT INTO veterinarians (name, phone, mail, address, city) VALUES ('Dr. Ayşe Demir', '5559014567', 'ayse.demir@example.com', 'Mithatpaşa Caddesi, 101. Sokak', 'Bursa');
INSERT INTO veterinarians (name, phone, mail, address, city) VALUES ('Dr. Murat Yıldız', '5550125678', 'murat.yildiz@example.com', 'Sakarya Mahallesi, 234. Cadde', 'Adana');
INSERT INTO veterinarians (name, phone, mail, address, city) VALUES ('Dr. Fatma Korkmaz', '5551236789', 'fatma.korkmaz@example.com', 'Yenimahalle, 567. Sokak', 'Antalya');
INSERT INTO veterinarians (name, phone, mail, address, city) VALUES ('Dr. Ahmet Ersoy', '5552347890', 'ahmet.ersoy@example.com', 'Çankaya Mahallesi, 890. Cadde', 'Konya');
INSERT INTO veterinarians (name, phone, mail, address, city) VALUES ('Dr. Hakan Çelik', '5553458901', 'hakan.celik@example.com', 'Karşıyaka Mahallesi, 123. Sokak', 'Mersin');
INSERT INTO veterinarians (name, phone, mail, address, city) VALUES ('Dr. Zeynep Uysal', '5554569012', 'zeynep.uysal@example.com', 'Bahçelievler Mahallesi, 456. Cadde', 'Eskişehir');
INSERT INTO veterinarians (name, phone, mail, address, city) VALUES ('Dr. Mustafa Aksoy', '5555670123', 'mustafa.aksoy@example.com', 'Beşiktaş Mahallesi, 789. Sokak', 'Gaziantep');

-- animals
INSERT INTO animals (name, species, breed, gender, colour, date_of_birth, customer_id) VALUES ('Pamuk', 'Dog', 'Golden Retriever', 'Female', 'Golden', '2022-03-20', 1);
INSERT INTO animals (name, species, breed, gender, colour, date_of_birth, customer_id) VALUES ('Şeker', 'Cat', 'British Shorthair', 'Male', 'Gray', '2023-07-10', 2);
INSERT INTO animals (name, species, breed, gender, colour, date_of_birth, customer_id) VALUES ('Fıstık', 'Hamster', 'Syrian', 'Male', 'Brown', '2020-11-25', 5);
INSERT INTO animals (name, species, breed, gender, colour, date_of_birth, customer_id) VALUES ('Zeytin', 'Rabbit', 'Holland Lop', 'Female', 'White', '2021-09-03', 3);
INSERT INTO animals (name, species, breed, gender, colour, date_of_birth, customer_id) VALUES ('Karabaş', 'Dog', 'Anatolian Shepherd', 'Male', 'Black', '2020-05-01', 1);

-- vaccines
INSERT INTO vaccines (name, code, protection_start_date, protection_end_date, animal_id) VALUES ('Distemper Vaccine', 'DV123', '2023-01-15', '2023-07-15', 1);
INSERT INTO vaccines (name, code, protection_start_date, protection_end_date, animal_id) VALUES ('Rabies Vaccine', 'RV456', '2023-05-20', '2023-11-20', 5);
INSERT INTO vaccines (name, code, protection_start_date, protection_end_date, animal_id) VALUES ('Feline Leukemia Vaccine', 'FLV789', '2022-08-10', '2023-02-10', 2);
INSERT INTO vaccines (name, code, protection_start_date, protection_end_date, animal_id) VALUES ('Feline Calicivirus Vaccine', 'FCV987', '2022-10-05', '2023-04-05', 2);
INSERT INTO vaccines (name, code, protection_start_date, protection_end_date, animal_id) VALUES ('Rodent Typhus Vaccine', 'RT456', '2021-03-01', '2021-09-01', 3);
INSERT INTO vaccines (name, code, protection_start_date, protection_end_date, animal_id) VALUES ('Hamster Hepatitis Vaccine', 'HH789', '2021-06-15', '2021-12-15', 3);
INSERT INTO vaccines (name, code, protection_start_date, protection_end_date, animal_id) VALUES ('Rabbit Viral Hemorrhagic Disease Vaccine', 'RVHD123', '2022-02-20', '2022-08-20', 4);
INSERT INTO vaccines (name, code, protection_start_date, protection_end_date, animal_id) VALUES ('Rabbit Calicivirus Vaccine', 'RCV456', '2022-04-10', '2022-10-10', 4);
INSERT INTO vaccines (name, code, protection_start_date, protection_end_date, animal_id) VALUES ('Canine Parvovirus Vaccine', 'CPV789', '2021-11-25', '2022-05-25', 1);
INSERT INTO vaccines (name, code, protection_start_date, protection_end_date, animal_id) VALUES ('Canine Distemper Vaccine', 'CDV987', '2022-01-01', '2022-07-01', 5);

-- available dates
INSERT INTO available_dates (available_date) VALUES ('2024-05-10');
INSERT INTO available_dates (available_date) VALUES ('2024-05-11');
INSERT INTO available_dates (available_date) VALUES ('2024-05-12');
INSERT INTO available_dates (available_date) VALUES ('2024-05-13');
INSERT INTO available_dates (available_date) VALUES ('2024-05-14');
INSERT INTO available_dates (available_date) VALUES ('2024-05-15');
INSERT INTO available_dates (available_date) VALUES ('2024-05-18');
INSERT INTO available_dates (available_date) VALUES ('2024-05-16');
INSERT INTO available_dates (available_date) VALUES ('2024-05-17');
