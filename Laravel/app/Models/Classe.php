<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use RoleUser;

class Classe extends Model
{
    use HasFactory;

    protected $fillable = [
        'abreviation',
        'nom',
        'departement_id',
    ];

    public function departement()
    {
        return $this->belongsTo(Departement::class);
    }

    public function matieres()
    {
        return $this->hasMany(Matiere::class, 'idClasse', 'id');
    }

    public function users()
    {
        return $this->hasMany('App\Models\RoleUser','classe','id');
    }



}
