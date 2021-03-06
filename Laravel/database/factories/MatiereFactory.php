<?php

namespace Database\Factories;

use App\Models\User;
use App\Models\Classe;
use Illuminate\Database\Eloquent\Factories\Factory;

class MatiereFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "nom" => $this->faker->word(),
            "idClasse" => Classe::get('id')->random(),
            "semestre" => 1,
            "coefficient" => 1,
            "idEnseignant"  => User::whereHas('roles', function ($query) {
                $query->where('name', 'enseignant');
            })->get()->random()->id,


        ];
    }
}
