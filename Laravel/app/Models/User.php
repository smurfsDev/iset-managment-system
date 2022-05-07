<?php

namespace App\Models;

use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

class User extends Authenticatable
{
    use HasApiTokens, HasFactory, Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'name',
        'email',
        'password',
    ];

    /**
     * The attributes that should be hidden for serialization.
     *
     * @var array<int, string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    /**
     * The attributes that should be cast.
     *
     * @var array<string, string>
     */
    protected $casts = [
        'email_verified_at' => 'datetime',
    ];

    public function club(){
        return $this->HasOne('App\Models\club', 'responsableClub');
    }

    public function demandeCreationClubs(){
        return $this->HasMany('App\Models\DemandeCreationClub', 'responsableClubId');
    }

    public function roles(){
        return $this->belongsToMany('App\Models\Role')->withPivot(['status','department','classe']);    }

    public function demandeAdhesionClub(){
        return $this->HasMany('App\Models\DemandeAdhesionClub', 'user_id');
    }

    public function demandeSalle(){
        return $this->HasMany('App\Models\DemandeSalle', 'idResponsable');
    }

    public function DemandeMateriel(){
        return $this->HasMany('App\Models\DemandeMateriel', 'idResponsableClub');
    }


}
