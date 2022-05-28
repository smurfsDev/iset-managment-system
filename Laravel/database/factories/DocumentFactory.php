<?php

namespace Database\Factories;

use App\Models\Classe;
use App\Models\DocumentCategorie;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

class DocumentFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            'nom' => $this->faker->word,
            'classes_id' => Classe::get('id')->random(),
            'document_categories_id' => DocumentCategorie::get('id')->random(), 
            'file' => $this->faker->sentence,
            'idResponsable' =>User::get('id')->random(),
            
        ];
    }
}
