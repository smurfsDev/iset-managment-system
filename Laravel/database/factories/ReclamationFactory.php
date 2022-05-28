<?php

namespace Database\Factories;

use App\Models\TypeCat;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class ReclamationFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "titre"=>$this->faker->word(),
            "description"=>$this->faker->sentence(),
            "file"=>$this->faker->word(),
            "type_cats_id"=>TypeCat::get('id')->random(),
            'idResponsable' =>User::get('id')->random(),
            'idAdmin' =>User::get('id')->random(),
        ];
    }
}
