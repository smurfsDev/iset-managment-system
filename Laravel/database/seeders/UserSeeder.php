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
        \App\Models\User::create([
            'name' => "admin",
            'email' => "admin@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        \App\Models\User::create([
            'name' => "student",
            'email' => "student@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);
        \App\Models\User::create([
            'name' => "responsableClub",
            'email' => "responsableClub@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);

        \App\Models\User::create([
            'name' => "studentetresponsable",
            'email' => "sr@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);

        \App\Models\User::create([
            'name' => "superAdmin",
            'email' => "sa@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);

        \App\Models\User::create([
            'name' => "chef department",
            'email' => "cd@example.com",
            'email_verified_at' => now(),
            'password' => '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', // password
            'remember_token' => Str::random(10),
        ]);

        DB::insert('insert into role_user (user_id, role_id) values (?, ?)', [1, 1]);
        DB::insert('insert into role_user (user_id, role_id,status) values (?, ?,?)', [2, 2,1]);
        DB::insert('insert into role_user (user_id, role_id,status) values (?, ?,?)', [4, 3,1]);
        DB::insert('insert into role_user (user_id, role_id) values (?, ?)', [3, 3]);
        DB::insert('insert into role_user (user_id, role_id) values (?, ?)', [4, 2]);
        DB::insert('insert into role_user (user_id, role_id) values (?, ?)', [5, 4]);
        DB::insert('insert into role_user (user_id, role_id,department,status) values (?,?, ?,?)', [5, 4,1,1]);


    }
}
