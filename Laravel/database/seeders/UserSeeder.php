<?php

namespace Database\Seeders;
use Illuminate\Support\Str;
use Illuminate\Support\Facades\DB;

use Illuminate\Database\Seeder;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // admin
        \App\Models\User::create([
            'name' => "admin",
            'email' => "admin@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        // student
        \App\Models\User::create([
            'name' => "student",
            'email' => "student@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        // responsable club
        \App\Models\User::create([
            'name' => "responsableClub",
            'email' => "responsableClub@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        // responsable club et student
        \App\Models\User::create([
            'name' => "studentetresponsable",
            'email' => "sr@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        // super admin
        \App\Models\User::create([
            'name' => "superAdmin",
            'email' => "sa@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        // chef de departement informatique
        \App\Models\User::create([
            'name' => "chef department informatique",
            'email' => "cdi@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        // chef de departement genie electrique
        \App\Models\User::create([
            'name' => "chef department génie electrique",
            'email' => "cde@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        // chef de departement genie mecanique
        \App\Models\User::create([
            'name' => "chef department génie mécanique",
            'email' => "cdm@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        // chef de departement Administration d'affaire
        \App\Models\User::create([
            'name' => "chef department Administration d'affaire",
            'email' => "cdeg@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        // chef de departement Génie des procédés
        \App\Models\User::create([
            'name' => "chef department Génie des procédés",
            'email' => "cdp@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        // Enseignant
        \App\Models\User::create([
            'name' => "enseignant",
            'email' => "enseignant@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);

        DB::insert('insert into role_user (user_id, role_id) values (?, ?)', [1, 1]);
        DB::insert('insert into role_user (user_id, role_id,status,classe) values (?, ?,?,?)', [2, 2,1,1]);
        DB::insert('insert into role_user (user_id, role_id,status) values (?, ?,?)', [4, 3,1]);
        DB::insert('insert into role_user (user_id, role_id) values (?, ?)', [3, 3]);
        DB::insert('insert into role_user (user_id, role_id) values (?, ?)', [4, 2]);
        DB::insert('insert into role_user (user_id, role_id) values (?, ?)', [5, 4]);

        DB::insert('insert into role_user (user_id, role_id,department,status) values (?,?, ?,?)', [6, 5,1,1]);
        DB::insert('insert into role_user (user_id, role_id,department,status) values (?,?, ?,?)', [7, 5,2,1]);
        DB::insert('insert into role_user (user_id, role_id,department,status) values (?,?, ?,?)', [8, 5,3,1]);
        DB::insert('insert into role_user (user_id, role_id,department,status) values (?,?, ?,?)', [9, 5,4,1]);
        DB::insert('insert into role_user (user_id, role_id,department,status) values (?,?, ?,?)', [10, 5,5,1]);
        DB::insert('insert into role_user (user_id, role_id,department,status) values (?,?, ?,?)', [11, 7,1,1]);
        



    }
}
