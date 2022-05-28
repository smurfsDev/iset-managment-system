<?php

namespace Database\Seeders;

use App\Models\Role;
use Illuminate\Database\Seeder;

class RolesSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        Role::create([
            'name' => 'admin',
        ]);
        Role::create([
            'name' => 'student',
        ]);
        Role::create([
            'name' => 'responsableClub',
        ]);
        Role::create([
            'name' => 'superAdmin'
        ]);
        Role::create([
            'name' => 'chefDepartement'
        ]);
        Role::create([
            'name' => 'technicien'
        ]);
        Role::create([
            'name' => 'enseignant'
        ]);

    }
}
